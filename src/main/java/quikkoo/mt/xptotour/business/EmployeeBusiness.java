package quikkoo.mt.xptotour.business;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import quikkoo.mt.xptotour.model.Employee;
import quikkoo.mt.xptotour.repository.EmployeeRepository;

@Named
@RequestScoped
public class EmployeeBusiness implements Serializable {

	private static final long serialVersionUID = 8881844518565929307L;

	@Inject
	private EmployeeRepository daoEmployee;

	public Employee login(String email, String password) {
		return daoEmployee.loadByEmailAndPassword(email, password);
	}
}
