package quikkoo.mt.xptotour.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import quikkoo.mt.xptotour.model.Customer;
import quikkoo.mt.xptotour.model.Employee;
import quikkoo.mt.xptotour.model.User;

@WebFilter("/*")
public class AuthenticationFilter implements Filter {

	private static final String MSG_USUARIO_INVALIDO =
			"O usuário do tipo %s não é reconhecido pelo sistema";

	private static final String URI_INDEX = AppController.VIEW_PATH + "/";
	private static final String URI_LOGIN = AppController.VIEW_PATH + AppController.VIEW_AUTHENTICATION;

	@Override
	public void init(FilterConfig config) throws ServletException {
	}

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		String uri = req.getRequestURI();

		if (isResourceURI(uri)) {
			doFilterForResource(request, response, chain);
		}
		else {
			doFilterForView(request, response, chain);
		}
	}

	public void doFilterForResource(ServletRequest request, ServletResponse response, FilterChain chain)
				throws IOException, ServletException {
		chain.doFilter(request, response);
	}

	public void doFilterForView(ServletRequest request, ServletResponse response, FilterChain chain)
				throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;

		if (isAuthenticated(req)) {
			doFilterAuthenticated(request, response, chain);
		}
		else {
			doFilterNotAuthenticated(request, response, chain);
		}
	}

	public void doFilterAuthenticated(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		String uri = req.getRequestURI();
		HttpSession session = req.getSession(false);

		if (isAuthorized(req)) {
			chain.doFilter(request, response);
		}
		else {
			if (isAuthenticationPage(uri)) {
				forward(request, response, getInitialPageFor(session));
			}
			else {
				forward(request, response, AppController.VIEW_NOT_AUTHORIZED);
			}
		}
	}

	public void doFilterNotAuthenticated(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		String uri = req.getRequestURI();

		if (isAuthenticationPage(uri)) {
			chain.doFilter(request, response);
		}
		else {
			forward(request, response, AppController.VIEW_AUTHENTICATION);
		}
	}

	public void forward(ServletRequest request, ServletResponse response, String path)
			throws ServletException, IOException {
		request.getRequestDispatcher(path)
			.forward(request, response);
	}

	private static boolean isResourceURI(String uri) {
		return uri.contains("javax.faces.resource");
	}

	private static boolean isAuthenticationPage(String uri) {
		return uri.equals(URI_INDEX) || uri.equals(URI_LOGIN);
	}

	private static boolean isAuthenticated(HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		User user = null;

		if (session != null) {
			user = (User) session.getAttribute(AppController.KEY_USER);
		}

		return user != null;
	}

	private boolean isAuthorized(HttpServletRequest req) {
		String uri = req.getRequestURI();
		HttpSession session = req.getSession(false);
		User user = (User) session.getAttribute(AppController.KEY_USER);

		if (user instanceof Customer) {
			return uri.contains("customer");
		}

		if (user instanceof Employee) {
			return uri.contains("employee");
		}

		throw new IllegalArgumentException(String.format(MSG_USUARIO_INVALIDO, user.getClass()));
	}

	private static String getInitialPageFor(HttpSession session) {
		User user = (User) session.getAttribute(AppController.KEY_USER);

		if (user instanceof Customer) {
			return AppController.VIEW_INITIAL_CUSTOMER;
		}

		if (user instanceof Employee) {
			return AppController.VIEW_INITIAL_EMPLOYEE;
		}

		throw new IllegalArgumentException(String.format(MSG_USUARIO_INVALIDO, user.getClass()));
	}

}
