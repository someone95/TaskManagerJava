package task.man.solved.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import javax.persistence.MappedSuperclass;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import task.man.solved.entities.BaseEntity;
import task.man.solved.entities.Comment;
import task.man.solved.entities.User;
import task.man.solved.entities.Worklog;
import task.man.solved.helper.ReflectionHelper;
import task.man.solved.services.BaseService;

@MappedSuperclass
public abstract class BaseController<T extends BaseEntity> {
	@Autowired
	protected BaseService<T> baseService;

	public ModelAndView create(HttpServletRequest request)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		T item = ReflectionHelper.CreateInstance(this.getClass(), 1);
		request.setAttribute("item", item);
		User logger = (User) request.getSession().getAttribute("LOGGEDIN_USER");
		ModelAndView mav = new ModelAndView(item.getClass().getSimpleName() + "Form");
		mav.addObject("userLogger", logger);
		return mav;
	}

	public ModelAndView edit(HttpServletRequest request)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		T item = ReflectionHelper.CreateInstance(this.getClass(), 1);
		request.setAttribute("item",
				item.getClass().cast(baseService.getById(Long.parseLong(request.getParameter("id")))));
		User logger = (User) request.getSession().getAttribute("LOGGEDIN_USER");
		ModelAndView mav = new ModelAndView(item.getClass().getSimpleName() + "Form");
		mav.addObject("userLogger", logger);
		return mav;
	}

	public ModelAndView delete(HttpServletRequest request)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		T item = ReflectionHelper.CreateInstance(this.getClass(), 1);
		if (item.getClass().getSimpleName().equals("Comment")) {
			Comment comm = (Comment) baseService.getById(Long.parseLong(request.getParameter("id")));
			baseService.delete(Long.parseLong(request.getParameter("id")));
			ModelAndView mav = new ModelAndView("redirect:getAllComments?parentId=" + comm.getTask().getId());
			return mav;
		}
		if (item.getClass().getSimpleName().equals("Worklog")) {
			Worklog worklog = (Worklog) baseService.getById(Long.parseLong(request.getParameter("id")));
			baseService.delete(Long.parseLong(request.getParameter("id")));
			ModelAndView mav = new ModelAndView("redirect:getAllWorklogs?parentId=" + worklog.getTask().getId());
			return mav;
		}
		baseService.delete(Long.parseLong(request.getParameter("id")));
		ModelAndView mav = new ModelAndView("redirect:getAll" + item.getClass().getSimpleName() + "s");
		return mav;

	}

	public ModelAndView save(@ModelAttribute T item, HttpServletRequest request)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		if (item.getId() == 0)
			baseService.add(item);
		else
			baseService.edit(item);
		ModelAndView mav = new ModelAndView("redirect:getAll" + item.getClass().getSimpleName() + "s");
		return mav;
	}

	public ModelAndView getAll(HttpServletRequest request) throws InstantiationException, IllegalAccessException {
		T item = ReflectionHelper.CreateInstance(this.getClass(), 1);
		List<T> ItemList = new ArrayList<T>();
		String searchName = request.getParameter("searchName" + item.getClass().getSimpleName());
		String searchColumn = request.getParameter("searchColumn" + item.getClass().getSimpleName());
		String sortColumn = request.getParameter("sortColumn" + item.getClass().getSimpleName());
		String sortOrder = request.getParameter("sortOrder" + item.getClass().getSimpleName());
		String pageString = request.getParameter("page" + item.getClass().getSimpleName());
		String itemsPerPage = request.getParameter("itemsPerPage" + item.getClass().getSimpleName());
		LinkedHashMap<String, String> columnNames = new LinkedHashMap<String, String>();
		feedSortLists(columnNames);
		Integer page = null;
		Integer items = 5;

		if (pageString != null) {
			page = Integer.parseInt(pageString);
		}
		if (itemsPerPage != null)
			items = Integer.parseInt(itemsPerPage);

		if (customList(request) != null)
			ItemList = customList(request);
		else
			ItemList = (List<T>) baseService.getAll();

		if (searchName != null && !searchName.equals(""))
			ItemList = filterAllByString(searchColumn, searchName, ItemList);

		if (sortColumn != null)
			sortByColumn(ItemList, sortOrder, sortColumn);

		PagedListHolder<T> PageList = generatePageList(ItemList, items, page);

		ModelAndView mav = new ModelAndView(item.getClass().getSimpleName() + "List");
		if (request.getRequestURI().equals("/content/Schedule/getAll")) {
			mav.addObject("itemList", ItemList);
			mav.addObject("ItemPageList", PageList);
		} else
			mav.addObject("itemList", PageList);

		mav.addObject("page", PageList.getPage());
		mav.addObject("maxPages", PageList.getPageCount());
		mav.addObject("searchName", searchName);
		mav.addObject("searchColumn", searchColumn);
		mav.addObject("sortColumn", sortColumn);
		mav.addObject("sortOrder", sortOrder);
		mav.addObject("itemsPerPage", items);
		mav.addObject("columnNames", columnNames);
		mav.addObject("itemClass", item);
		return mav;
	}

	public ModelAndView getAll(HttpServletRequest request, @RequestParam long parentId)
			throws InstantiationException, IllegalAccessException {
		T item = ReflectionHelper.CreateInstance(this.getClass(), 1);
		List<T> ItemList = new ArrayList<T>();
		String searchName = request.getParameter("searchName" + item.getClass().getSimpleName());
		String searchColumn = request.getParameter("searchColumn" + item.getClass().getSimpleName());
		String sortColumn = request.getParameter("sortColumn" + item.getClass().getSimpleName());
		String sortOrder = request.getParameter("sortOrder" + item.getClass().getSimpleName());
		String pageString = request.getParameter("page" + item.getClass().getSimpleName());
		String itemsPerPage = request.getParameter("itemsPerPage" + item.getClass().getSimpleName());
		LinkedHashMap<String, String> columnNames = new LinkedHashMap<String, String>();
		feedSortLists(columnNames);
		Integer page = null;
		Integer items = 5;

		if (pageString != null) {
			page = Integer.parseInt(pageString);
		}
		if (itemsPerPage != null)
			items = Integer.parseInt(itemsPerPage);

		if (customList(request) != null)
			ItemList = customList(request);
		else if (customChildList(request, parentId) != null)
			ItemList = customChildList(request, parentId);
		else
			ItemList = (List<T>) baseService.getAll();

		if (searchName != null && !searchName.equals(""))
			ItemList = filterAllByString(searchColumn, searchName, ItemList);

		if (sortColumn != null)
			sortByColumn(ItemList, sortOrder, sortColumn);

		PagedListHolder<T> PageList = generatePageList(ItemList, items, page);

		ModelAndView mav = new ModelAndView(item.getClass().getSimpleName() + "List");
		if (request.getRequestURI().equals("/solved/getAll" + item.getClass().getSimpleName())) {
			mav.addObject("itemList", ItemList);
			mav.addObject("ItemPageList", PageList);
		} else {
			mav.addObject("itemList", PageList);

		}
		mav.addObject("parentId", parentId);
		mav.addObject("page", PageList.getPage());
		mav.addObject("maxPages", PageList.getPageCount());
		mav.addObject("searchName", searchName);
		mav.addObject("searchColumn", searchColumn);
		mav.addObject("sortColumn", sortColumn);
		mav.addObject("sortOrder", sortOrder);
		mav.addObject("itemsPerPage", items);
		mav.addObject("columnNames", columnNames);
		mav.addObject("itemClass", item);
		return mav;
	}

	public PagedListHolder<T> generatePageList(List<T> list, Integer size, Integer page) {
		PagedListHolder<T> PageList = new PagedListHolder<T>(list);
		PageList.setPageSize(size);
		if (page == null)
			PageList.setPage(0);
		else
			PageList.setPage(page);
		return PageList;
	}

	public void sortByColumn(List<T> List, String order, String column) {
		switch (column) {
		case "id":
			Collections.sort(List, (p1, p2) -> (new Long(p2.getId()).compareTo(new Long(p1.getId()))));
			break;

		default:
			feedSort(List, column);
			break;
		}
		if (order.compareTo("descending") == 0)
			Collections.reverse(List);
	}

	public abstract void feedSort(List<T> List, String column);

	public abstract void feedSortLists(LinkedHashMap<String, String> Map);

	public abstract List<T> customList(HttpServletRequest request)
			throws InstantiationException, IllegalAccessException;

	public abstract List<T> filterAllByString(String searchColumn, String searchName, List<T> result);

	public abstract List<T> customChildList(HttpServletRequest request, @RequestParam long parentId)
			throws InstantiationException, IllegalAccessException;
}
