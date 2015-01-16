package quikkoo.mt.xptotour.controller;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJBException;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import quikkoo.mt.xptotour.business.CustomerBusiness;
import quikkoo.mt.xptotour.business.EmployeeBusiness;
import quikkoo.mt.xptotour.model.Customer;
import quikkoo.mt.xptotour.model.Employee;

@ManagedBean(name = "autc")
@RequestScoped
public class AuthenticationController implements Serializable {

	private static final long serialVersionUID = -5984970389242036515L;

	private static final String MSG_LOGIN                = "Bem vindo %s";
	private static final String MSG_LOGOUT               = "Sessão terminada";
	private static final String MSG_ERROR_AUTHENTICATION = "Erro de autenticação";
	private static final String MSG_INVALID_EMAIL_CPF    = "Email ou Cpf incorretos";
	private static final String MSG_INVALID_EMAIL_SENHA  = "Email ou Senha incorretos";

	@Inject
	private CustomerBusiness boCustomer;
	@Inject
	private EmployeeBusiness boEmployee;

	private String email;
	private String cpf;
	private String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String loginCustomer() {
		try {
			Customer customer = boCustomer.login(email, cpf);
			AppController.session()
				.setAttribute(AppController.KEY_USER, customer);

			AppController.addInfoMessage(String.format(MSG_LOGIN, customer.getName()), null);

			return AppController.VIEW_INITIAL_CUSTOMER;
		}
		catch (EJBException e) {
			AppController.addErrorMessage(MSG_ERROR_AUTHENTICATION, MSG_INVALID_EMAIL_CPF);

			return AppController.VIEW_AUTHENTICATION;
		}
	}

	public String loginEmployee() {
		try {
			Employee employee = boEmployee.login(email, password);
			AppController.session()
				.setAttribute(AppController.KEY_USER, employee);

			AppController.addInfoMessage(String.format(MSG_LOGIN, employee.getName()), null);

			return AppController.VIEW_INITIAL_EMPLOYEE;
		}
		catch (EJBException e) {
			AppController.addErrorMessage(MSG_ERROR_AUTHENTICATION, MSG_INVALID_EMAIL_SENHA);

			return AppController.VIEW_AUTHENTICATION;
		}
	}

	public String logout() {
		AppController.session()
			.invalidate();

		AppController.addInfoMessage(MSG_LOGOUT, null);

		return AppController.VIEW_AUTHENTICATION;
	}

	public List<Customer> getCustomers() {
		return boCustomer.list();
	}
}
