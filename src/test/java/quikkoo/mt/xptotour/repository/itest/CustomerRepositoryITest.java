package quikkoo.mt.xptotour.repository.itest;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import javax.ejb.EJBException;
import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import quikkoo.mt.xptotour.model.Customer;
import quikkoo.mt.xptotour.repository.CustomerRepository;
import quikkoo.mt.xptotour.test.Deployments;

@RunWith(Arquillian.class)
public class CustomerRepositoryITest {

	@Deployment
	public static Archive<WebArchive> deployment() {
		return Deployments.forPersistence();
	}

	@Inject
	private CustomerRepository daoCustomer;

	@Before
	public void before() {
		daoCustomer.insert(new Customer("Ana", "ana@email.org", "000.000.000-00", "00 0000-0000"));
	}

	@After
	public void after() {
		daoCustomer.erase();
	}

	@Test
	public void testLoadCustomerByEmailAndCpf() {
		Customer customer = daoCustomer.loadByEmailAndCpf("ana@email.org", "000.000.000-00");

		assertThat(customer, notNullValue());
		assertThat(customer.getId(), greaterThan(0L));
		assertThat(customer.getEmail(), is("ana@email.org"));
		assertThat(customer.getCpf(), is("000.000.000-00"));
	}

	@Test(expected = EJBException.class)
	public void testLoadCustomerByInvalidEmailAndCpf() {
		daoCustomer.loadByEmailAndCpf("a@a.org", "000.000.000-00");

		fail("Carregar cliente com email inválido deve lançar exceção");
	}

	@Test(expected = EJBException.class)
	public void testLoadCustomerByEmailAndInvalidCpf() {
		daoCustomer.loadByEmailAndCpf("ana@email.org", "0.0.0-0");

		fail("Carregar cliente com cpf inválido deve lançar exceção");
	}
}
