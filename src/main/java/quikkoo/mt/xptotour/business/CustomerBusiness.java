package quikkoo.mt.xptotour.business;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import quikkoo.mt.xptotour.model.Customer;
import quikkoo.mt.xptotour.repository.CustomerRepository;

@Named
@RequestScoped
public class CustomerBusiness implements Serializable {

	private static final long serialVersionUID = -1102333629747461600L;

	@Inject
	private CustomerRepository daoCustomer;

	public Customer login(String email, String cpf) {
		return daoCustomer.loadByEmailAndCpf(email, cpf);
	}

	public List<Customer> list() {
		return daoCustomer.find();
	}
}
