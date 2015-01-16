package quikkoo.mt.xptotour.repository;

import java.io.Serializable;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import quikkoo.mt.xptotour.model.Employee;

@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class EmployeeRepository extends JpaRepository<Employee, Long> implements Serializable {

	private static final long serialVersionUID = -3588130673004522710L;

	public EmployeeRepository() {
		super(Employee.class);
	}

	@Override
	public List<Employee> find() {
		return createNamedQuery("Employee.find")
				.getResultList();
	}

	public Employee loadByEmailAndPassword(String email, String password) {
		return createNamedQuery("Employee.loadByEmailAndPassword")
				.setParameter("email", email)
				.setParameter("password", password)
				.getSingleResult();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void erase() {
		createNamedQuery("Employee.erase").executeUpdate();
	}
}
