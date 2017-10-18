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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import task.man.solved.entities.Task;
import task.man.solved.entities.Worklog;
import task.man.solved.services.TasksService;

@Controller
public class WorklogController extends BaseController<Worklog> {
	@Autowired
	private TasksService taskService;

	@RequestMapping("addWorklog")
	public ModelAndView addWorklog(HttpServletRequest request, @RequestParam long parentTaskId)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		request.getSession().setAttribute("parentTaskId", parentTaskId);
		return create(request);
	}

	@RequestMapping("editWorklog")
	public ModelAndView editWorklog(HttpServletRequest request)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		return edit(request);
	}

	@RequestMapping("saveWorklog")
	public ModelAndView saveWorklog(@ModelAttribute Worklog item, HttpServletRequest request)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		return save(item, request);
	}

	@RequestMapping("deleteWorklog")
	public ModelAndView deleteWorklog(HttpServletRequest request)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		return delete(request);
	}

	@RequestMapping("getAllWorklogs")
	public ModelAndView getAllWorklogs(HttpServletRequest request, @RequestParam long parentId)
			throws InstantiationException, IllegalAccessException {
		request.setAttribute("parentId", parentId);
		return getAll(request, parentId);
	}

	@Override
	public void feedSort(List<Worklog> List, String column) {
		// TODO Auto-generated method stub
		switch (column) {
		case "Task":
			Collections.sort(List, (p1, p2) -> p1.getTask().getTitle().compareTo(p2.getTask().getTitle()));
			break;
		case "Hours":
			Collections.sort(List, (p1, p2) -> new Integer(p1.getHours()).compareTo(new Integer(p2.getHours())));
			break;
		default:
			break;
		}
	}

	@Override
	public void feedSortLists(LinkedHashMap<String, String> Map) {
		// TODO Auto-generated method stub
		Map.put("task", "Task");
		Map.put("hours", "Hours");
		Map.put("dateWorklog", "Date Worklog");
	}

	@Override
	public List<Worklog> customList(HttpServletRequest request) throws InstantiationException, IllegalAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Worklog> filterAllByString(String column, String searchName, List<Worklog> result) {
		// TODO Auto-generated method stub
		switch (column) {
		case "Task":
			return (ArrayList<Worklog>) result.stream().filter(p -> p.getTask().getTitle().contains(searchName))
					.collect(Collectors.toList());
		case "Hours":
			return (ArrayList<Worklog>) result.stream().filter(p -> p.getHours() == Integer.parseInt(searchName))
					.collect(Collectors.toList());
		case "Date Worklog":
			return (ArrayList<Worklog>) result.stream().filter(p -> p.getDateWorklog().toString().contains(searchName))
					.collect(Collectors.toList());
		}
		return result;
	}

	@Override
	public List<Worklog> customChildList(HttpServletRequest request,@RequestParam long parentId)
			throws InstantiationException, IllegalAccessException {
		// TODO Auto-generated method stub
		Task task = taskService.getById(parentId);
		String gomna = task.getTitle();
		Set<Worklog> workList = task.getWorklogs();
		request.setAttribute("gomna", gomna);
		request.setAttribute("itemList", workList);
		List<Worklog> result = new ArrayList<Worklog>();
		for (Worklog worklog : workList) {
			result.add(worklog);
		}
		return result;
	}

}
