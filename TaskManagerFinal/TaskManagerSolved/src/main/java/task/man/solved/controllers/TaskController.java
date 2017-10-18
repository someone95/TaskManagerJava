package task.man.solved.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import task.man.solved.entities.Comment;
import task.man.solved.entities.Task;
import task.man.solved.entities.User;
import task.man.solved.entities.Worklog;
import task.man.solved.services.CommentsService;
import task.man.solved.services.TasksService;
import task.man.solved.services.UsersService;
import task.man.solved.services.WorklogServices;

@Controller
public class TaskController extends BaseController<Task> {

	@Autowired
	private UsersService userService;
	@Autowired
	private TasksService taskService;
	@Autowired
	private CommentsService comService;
	@Autowired
	private WorklogServices workService;

	@RequestMapping("addTask")
	public ModelAndView addTask(HttpServletRequest request)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		List<User> userList = userService.getAll();
		request.setAttribute("userList", userList);
		return create(request);
	}

	@RequestMapping("editTask")
	public ModelAndView editTask(HttpServletRequest request)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		List<User> userList = userService.getAll();
		request.setAttribute("userList", userList);
		return edit(request);
	}

	@RequestMapping("deleteTask")
	public ModelAndView deleteTask(HttpServletRequest request)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Task task = taskService.getById(Long.parseLong(request.getParameter("id")));
		Set<Comment> com = (Set<Comment>) task.getComments();
		Set<Worklog> worklogs = (Set<Worklog>) task.getWorklogs();
		for (Comment comment : com) {
			comService.delete(comment.getId());
		}
		for (Worklog worklog : worklogs) {
			workService.delete(worklog.getId());
		}
		return delete(request);
	}

	@RequestMapping("saveTask")
	public ModelAndView saveTask(@ModelAttribute Task item, HttpServletRequest request)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		User user = (User) request.getSession().getAttribute("LOGGEDIN_USER");
		item.setCreator(user);
		return save(item, request);
	}

	@RequestMapping("getAllTasks")
	public ModelAndView getAllTasks(HttpServletRequest request) throws InstantiationException, IllegalAccessException {

		// ModelAndView mv = new ModelAndView("TaskList", "ItemList", taskList);
		// mv.addObject("itemList", assignedList);
		// mv.addObject("loggedUser", user);

		return getAll(request);
	}

	@Override
	public void feedSort(List<Task> List, String column) {
		switch (column) {
		case "Title": {
			Collections.sort(List, (p1, p2) -> p1.getTitle().compareTo(p2.getTitle()));
			break;
		}
		case "Content": {
			Collections.sort(List, (p1, p2) -> p1.getContent().compareTo(p2.getContent()));
			break;
		}
		case "Date Of Creation": {
			Collections.sort(List, (p1, p2) -> new Long(p1.getDateOfCreation().getTime())
					.compareTo(new Long(p2.getDateOfCreation().getTime())));
			break;
		}
		case "Date Of Last Edit": {
			Collections.sort(List, (p1, p2) -> new Long(p1.getDateOfLastEdit().getTime())
					.compareTo(new Long(p2.getDateOfLastEdit().getTime())));
			break;
		}
		case "Creator": {
			Collections.sort(List, (p1, p2) -> p1.getCreator().getUsername().compareTo(p2.getCreator().getUsername()));
			break;
		}
		case "Assigned User": {
			Collections.sort(List,
					(p1, p2) -> p1.getAssignedUser().getUsername().compareTo(p2.getAssignedUser().getUsername()));
			break;
		}
		case "Mark": {
			Collections.sort(List, (p1, p2) -> new Double(p1.getMark()).compareTo(new Double(p2.getMark())));
			break;
		}

		default:
			break;
		}
	}

	@Override
	public void feedSortLists(LinkedHashMap<String, String> Map) {
		Map.put("title", "Title");
		Map.put("content", "Content");
		Map.put("dateOfCreation", "Date Of Creation");
		Map.put("dateOfLastEdit", "Date Of Last Edit");
		Map.put("mark", "Mark");
		Map.put("creator", "Creator");
		Map.put("assignedUser", "Assigned User");
	}

	@Override
	public List<Task> customList(HttpServletRequest request) throws InstantiationException, IllegalAccessException {
		User logger = (User) request.getSession().getAttribute("LOGGEDIN_USER");
		User user = userService.getById(logger.getId());
		Set<Task> taskList = user.getTasks();
		Set<Task> assignedList = user.getAssignedTasks();
		request.setAttribute("itemList", taskList);
		request.setAttribute("itemList", assignedList);
		request.setAttribute("loggedUser", user);

		List<Task> result = new ArrayList<Task>();

		for (Task task : assignedList) {
			result.add(task);
			for (Task t : taskList) {
				if (task.getCreator().getId() != t.getCreator().getId()) {
					result.add(t);
				}
			}
		}
		return result;
	}

	@Override
	public List<Task> filterAllByString(String column, String searchName, List<Task> result) {
		switch (column) {
		case "Title":
			return (ArrayList<Task>) result.stream().filter(p -> p.getTitle().contains(searchName))
					.collect(Collectors.toList());
		case "Content":
			return (ArrayList<Task>) result.stream().filter(p -> p.getContent().contains(searchName))
					.collect(Collectors.toList());
		case "Date of Creation":
			return (ArrayList<Task>) result.stream()
					.filter(p -> (p.getDateOfCreation().toString()).contains(searchName)).collect(Collectors.toList());
		case "Date of Last Edit":
			return (ArrayList<Task>) result.stream()
					.filter(p -> (p.getDateOfLastEdit().toString()).contains(searchName)).collect(Collectors.toList());
		case "Creator":
			return (ArrayList<Task>) result.stream().filter(p -> (p.getCreator().getUsername()).contains(searchName))
					.collect(Collectors.toList());
		case "Assigned User":
			return (ArrayList<Task>) result.stream()
					.filter(p -> (p.getAssignedUser().getUsername()).contains(searchName)).collect(Collectors.toList());
		case "Mark":
			return (ArrayList<Task>) result.stream().filter(p -> p.getMark() == Double.parseDouble(searchName))
					.collect(Collectors.toList());
		}
		return result;
	}

	@Override
	public List<Task> customChildList(HttpServletRequest request, long id) {
		// TODO Auto-generated method stub
		return null;
	}
}
