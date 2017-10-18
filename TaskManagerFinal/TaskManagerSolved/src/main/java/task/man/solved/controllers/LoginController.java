package task.man.solved.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import task.man.solved.entities.User;
import task.man.solved.services.UsersService;

@Controller
public class LoginController {
	@Autowired
	private UsersService usersService;

	@RequestMapping(value = { "home", "/" })
	public ModelAndView login() {
		return new ModelAndView("loginForm");
	}

	@RequestMapping("userLogin")
	public ModelAndView userLogin(@ModelAttribute User user, HttpServletRequest request)
			throws InstantiationException, IllegalAccessException {
		String username = user.getUsername();
		String password = user.getPassword();
		User logger = new User();
		logger = usersService.getByUsernamePassword(username, password);
		if (logger != null) {
			request.getSession().setAttribute("LOGGEDIN_USER", logger);
			if (logger.isAdmin() == true) {
				return new ModelAndView("redirect:getAllUsers");
			} else {
				return new ModelAndView("redirect:getAllTasks");
			}
		}
		ModelAndView mv = new ModelAndView("redirect:home");
		mv.getModel().put("ERROR", "Wrong username or password");
		return mv;
	}
	@RequestMapping("logout")
	public ModelAndView logout(@ModelAttribute User user, HttpServletRequest request){
		request.getSession().removeAttribute("LOGGEDIN_USER");
		return new ModelAndView("redirect:home");
		
	}
}
