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

import quikkoo.mt.xptotour.model.Employee;
import quikkoo.mt.xptotour.repository.EmployeeRepository;
import quikkoo.mt.xptotour.test.Deployments;

@RunWith(Arquillian.class)
public class EmployeeRepositoryITest {

	@Deployment
	public static Archive<WebArchive> deployment() {
		return Deployments.forPersistence();
	}

	@Inject
	private EmployeeRepository daoEmployee;

	@Before
	public void before() {
		daoEmployee.insert(new Employee("Admin", "admin@email.org", "admin"));
	}

	@After
	public void after() {
		daoEmployee.erase();
	}

	@Test
	public void testLoadEmployeeByEmailAndCpf() {
		Employee cemployee = daoEmployee.loadByEmailAndPassword("admin@email.org", "admin");

		assertThat(cemployee, notNullValue());
		assertThat(cemployee.getId(), greaterThan(0L));
		assertThat(cemployee.getEmail(), is("admin@email.org"));
		assertThat(cemployee.getPassword(), is("admin"));
	}

	@Test(expected = EJBException.class)
	public void testLoadEmployeeByInvalidEmailAndPassword() {
		daoEmployee.loadByEmailAndPassword("a@a.org", "admin");

		fail("Carregar funcionário com email inválido deve lançar exceção");
	}

	@Test(expected = EJBException.class)
	public void testLoadEmployeeByEmailAndInvalidPassword() {
		daoEmployee.loadByEmailAndPassword("admin@email.org", "aaaaa");

		fail("Carregar funcionário com senha inválida deve lançar exceção");
	}
}
