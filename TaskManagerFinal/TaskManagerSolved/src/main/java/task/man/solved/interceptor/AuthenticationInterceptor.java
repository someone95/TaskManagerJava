package task.man.solved.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import task.man.solved.entities.User;

public class AuthenticationInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (!request.getRequestURI().equals("/solved/addUser") && !request.getRequestURI().equals("/solved/userLogin")
				&& !request.getRequestURI().equals("/solved/home")
				&& !request.getRequestURI().equals("/solved/saveUser")) {

			User userData = (User) request.getSession().getAttribute("LOGGEDIN_USER");
			if (userData == null) {
				response.sendRedirect("home");
				return false;
			}
		}

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
