package quikkoo.mt.xptotour.controller;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.servlet.http.HttpSession;

public final class AppController {

	public static final String VIEW_PATH             = "/xptotour";
	public static final String VIEW_AUTHENTICATION   = "/index.xhtml";
	public static final String VIEW_INITIAL_CUSTOMER = "/customer.xhtml";
	public static final String VIEW_INITIAL_EMPLOYEE = "/employee.xhtml";

	public static final String VIEW_NOT_AUTHORIZED   = "/403.xhtml";

	public static final String KEY_USER = "user";

	private AppController() {
	}

	public static Flash flash() {
		return external().getFlash();
	}

	@SuppressWarnings("unchecked")
	public static <T> T flash(String param) {
		return (T) flash().get(param);
	}

	public static void flash(String param, Object value, boolean redirect) {
		Flash flash = flash();
		flash.put(param, value);
		flash.setRedirect(redirect);
	}

	public static void redirect(String path) throws IOException {
		external().redirect(path);
	}

	public static HttpSession session() {
		return (HttpSession) external().getSession(false);
	}

	public static void addInfoMessage(String summary, String detail) {
		addMessage(FacesMessage.SEVERITY_INFO, summary, detail);
	}

	public static void addWarnMessage(String summary, String detail) {
		addMessage(FacesMessage.SEVERITY_WARN, summary, detail);
	}

	public static void addErrorMessage(String summary, String detail) {
		addMessage(FacesMessage.SEVERITY_ERROR, summary, detail);
	}

	public static void addFatalMessage(String summary, String detail) {
		addMessage(FacesMessage.SEVERITY_FATAL, summary, detail);
	}

	private static FacesContext faces() {
		return FacesContext.getCurrentInstance();
	}

	private static ExternalContext external() {
		return faces().getExternalContext();
	}

	private static void addMessage(FacesMessage.Severity severity, String summary, String detail) {
		FacesMessage msg = new FacesMessage(severity, summary, detail);
		faces().addMessage(null, msg);
	}
}
