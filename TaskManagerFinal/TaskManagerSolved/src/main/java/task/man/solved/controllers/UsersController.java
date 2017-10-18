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
public class UsersController extends BaseController<User> {
	@Autowired
	private UsersService userService;
	@Autowired
	private TasksService taskService;
	@Autowired
	private WorklogServices workService;
	@Autowired
	private CommentsService comService;

	@RequestMapping("addUser")
	public ModelAndView addUser(HttpServletRequest request)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		return create(request);
	}

	@RequestMapping("editUser")
	public ModelAndView editUser(HttpServletRequest request)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		return edit(request);
	}

	@RequestMapping("deleteUser")
	public ModelAndView deleteUser(HttpServletRequest request)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		User user = userService.getById(Long.parseLong(request.getParameter("id")));

		Set<Task> tasks = user.getAssignedTasks();
		for (Task task : tasks) {

			Set<Comment> com = (Set<Comment>) task.getComments();
			Set<Worklog> worklogs = (Set<Worklog>) task.getWorklogs();
			for (Comment comment : com) {
				comService.delete(comment.getId());
			}
			for (Worklog worklog : worklogs) {
				workService.delete(worklog.getId());
			}
			taskService.delete(task.getId());

		}
		Set<Task> taskss = user.getTasks();
		for (Task task : taskss) {
			Set<Comment> com = (Set<Comment>) task.getComments();
			Set<Worklog> worklogs = (Set<Worklog>) task.getWorklogs();
			for (Comment comment : com) {
				comService.delete(comment.getId());
			}
			for (Worklog worklog : worklogs) {
				workService.delete(worklog.getId());
			}
			taskService.delete(task.getId());
		}

		return delete(request);
	}

	@RequestMapping("saveUser")
	public ModelAndView saveUser(@ModelAttribute User item, HttpServletRequest request)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		return save(item, request);
	}

	@RequestMapping("getAllUsers")
	public ModelAndView getAllUsers(HttpServletRequest request) throws InstantiationException, IllegalAccessException {
		return getAll(request);
	}

	@Override
	public void feedSort(List<User> List, String column) {
		// TODO Auto-generated method stub
		switch (column) {
		case "First Name": {
			Collections.sort(List, (p1, p2) -> p1.getFirstName().compareTo(p2.getUsername()));
			break;
		}

		case "Last Name": {
			Collections.sort(List, (p1, p2) -> p1.getLastName().compareTo(p2.getLastName()));
			break;
		}
		case "Username": {
			Collections.sort(List, (p1, p2) -> p1.getUsername().compareTo(p2.getUsername()));
			break;
		}
		case "Password": {
			Collections.sort(List, (p1, p2) -> p1.getPassword().compareTo(p2.getPassword()));
			break;
		}
		case "Admin": {
			Collections.sort(List, (p1, p2) -> new Boolean(p1.isAdmin()).compareTo(new Boolean(p2.isAdmin())));
			break;
		}
		default:
			break;
		}
	}

	@Override
	public void feedSortLists(LinkedHashMap<String, String> Map) {
		// TODO Auto-generated method stub
		Map.put("firstName", "First Name");
		Map.put("lastName", "Last Name");
		Map.put("username", "Username");
		Map.put("password", "Password");
		Map.put("admin", "Admin");
	}

	@Override
	public List<User> customList(HttpServletRequest request) throws InstantiationException, IllegalAccessException {
		// TODO Auto-generated method stub

		return null;
	}

	@Override
	public List<User> filterAllByString(String column, String searchName, List<User> result) {
		// TODO Auto-generated method stub
		switch (column) {
		case "First Name":
			return (ArrayList<User>) result.stream().filter(p -> p.getFirstName().contains(searchName))
					.collect(Collectors.toList());
		case "Last Name":
			return (ArrayList<User>) result.stream().filter(p -> p.getLastName().contains(searchName))
					.collect(Collectors.toList());
		case "Username":
			return (ArrayList<User>) result.stream().filter(p -> p.getUsername().contains(searchName))
					.collect(Collectors.toList());
		case "Password":
			return (ArrayList<User>) result.stream().filter(p -> p.getPassword().contains(searchName))
					.collect(Collectors.toList());
		case "Admin":
			if (searchName.equalsIgnoreCase("true"))
				return (ArrayList<User>) result.stream().filter(p -> p.isAdmin() == true).collect(Collectors.toList());
			else
				return (ArrayList<User>) result.stream().filter(p -> p.isAdmin() == false).collect(Collectors.toList());
		}
		return result;

	}

	@Override
	public List<User> customChildList(HttpServletRequest request, long id) {
		// TODO Auto-generated method stub
		return null;
	}

}