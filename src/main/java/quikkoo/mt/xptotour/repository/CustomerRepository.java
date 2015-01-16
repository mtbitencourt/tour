package quikkoo.mt.xptotour.repository;

import java.io.Serializable;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import quikkoo.mt.xptotour.model.Customer;

@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class CustomerRepository extends JpaRepository<Customer, Long> implements Serializable {

	private static final long serialVersionUID = 7696891367909405488L;

	public CustomerRepository() {
		super(Customer.class);
	}

	@Override
	public List<Customer> find() {
		return createNamedQuery("Customer.find")
				.getResultList();
	}

	public Customer loadByEmailAndCpf(String email, String cpf) {
		return createNamedQuery("Customer.loadByEmailAndCpf")
				.setParameter("email", email)
				.setParameter("cpf", cpf)
				.getSingleResult();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void erase() {
		createNamedQuery("Customer.erase").executeUpdate();
	}
}
