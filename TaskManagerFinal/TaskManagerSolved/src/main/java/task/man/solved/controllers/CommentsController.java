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
import task.man.solved.entities.Comment;
import task.man.solved.entities.Task;
import task.man.solved.services.TasksService;

@Controller
public class CommentsController extends BaseController<Comment> {

	@Autowired
	private TasksService taskService;

	@RequestMapping("addComment")
	public ModelAndView addCommentt(HttpServletRequest request, @RequestParam long parentTaskId)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		request.setAttribute("parentTaskId", parentTaskId);
		return create(request);
	}

	@RequestMapping("editComment")
	public ModelAndView editComment(HttpServletRequest request)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		return edit(request);
	}

	@RequestMapping("saveComment")
	public ModelAndView saveComment(@ModelAttribute Comment item, HttpServletRequest request)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		return save(item, request);
	}

	@RequestMapping("deleteComment")
	public ModelAndView deleteComment(HttpServletRequest request)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		return delete(request);
	}

	@RequestMapping("getAllComments")
	public ModelAndView getAllComments(HttpServletRequest request, @RequestParam long parentId)
			throws InstantiationException, IllegalAccessException {
		request.setAttribute("parentId", parentId);
		return getAll(request, parentId);
	}

	@Override
	public void feedSort(List<Comment> List, String column) {
		switch (column) {
		case "Task":
			Collections.sort(List, (p1, p2) -> p1.getTask().getTitle().compareTo(p2.getTask().getTitle()));
			break;
		case "Description":
			Collections.sort(List, (p1, p2) -> p1.getDescription().compareTo(p2.getDescription()));
		default:
			break;
		}
	}

	@Override
	public void feedSortLists(LinkedHashMap<String, String> Map) {
		Map.put("task", "Task");
		Map.put("description", "Description");
	}

	@Override
	public List<Comment> customList(HttpServletRequest request) throws InstantiationException, IllegalAccessException {
		return null;
	}

	@Override
	public List<Comment> filterAllByString(String column, String searchName, List<Comment> result) {
		switch (column) {
		case "Task":
			return (ArrayList<Comment>) result.stream().filter(p -> p.getTask().getTitle().contains(searchName))
					.collect(Collectors.toList());
		case "Description":
			return (ArrayList<Comment>) result.stream().filter(p -> p.getDescription().contains(searchName))
					.collect(Collectors.toList());
		}
		return result;
	}

	@Override
	public List<Comment> customChildList(HttpServletRequest request, @RequestParam long parentId)
			throws InstantiationException, IllegalAccessException {
		Task task = taskService.getById(parentId);
		String gomna = task.getTitle();
		Set<Comment> comList = task.getComments();
		request.setAttribute("itemList", comList);
		request.setAttribute("gomna", gomna);
		List<Comment> result = new ArrayList<Comment>();
		for (Comment comment : comList) {
			result.add(comment);
		}
		return result;
	}

}
