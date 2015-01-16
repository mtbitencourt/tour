package quikkoo.mt.xptotour;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import quikkoo.mt.xptotour.model.Customer;
import quikkoo.mt.xptotour.model.Destination;
import quikkoo.mt.xptotour.model.Employee;
import quikkoo.mt.xptotour.model.Trip;
import quikkoo.mt.xptotour.repository.CustomerRepository;
import quikkoo.mt.xptotour.repository.DestinationRepository;
import quikkoo.mt.xptotour.repository.EmployeeRepository;
import quikkoo.mt.xptotour.repository.TripRepository;

import br.com.six2six.fixturefactory.function.DateTimeFunction;
import br.com.six2six.fixturefactory.function.NameFunction;
import br.com.six2six.fixturefactory.util.DateTimeUtil;

@WebListener
public class InitialData implements ServletContextListener {

	@Inject
	private CustomerRepository daoCustomer;
	@Inject
	private EmployeeRepository daoEmployee;
	@Inject
	private DestinationRepository daoDestination;
	@Inject
	private TripRepository daoTrip;

	private final Random random = new Random();

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		try {
			Context context = new InitialContext();
			context.bind("inject", this);
		}
		catch (NamingException e) {
			e.printStackTrace();
		}

		daoCustomer.erase();
		daoEmployee.erase();
		daoDestination.erase();
		daoTrip.erase();

		customers();
		employees();
		destinations();
		trips();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}

	private String getRandomPhone() {
		return String.format("%02d %04d-%04d",
				random.nextInt(99), random.nextInt(9999), random.nextInt(9999));
	}

	private String getRandomCpf() {
		return String.format("%03d.%03d.%03d-%02d",
				random.nextInt(999), random.nextInt(999), random.nextInt(999), random.nextInt(99));
	}

	private void customers() {
		List<Customer> list = new ArrayList<Customer>();
		for (int i = 0; i < 20; i++) {
			String name = new NameFunction().generateValue();
			list.add(new Customer(name, name.split(" ")[0].toLowerCase() + "@email.org",
					getRandomCpf(), getRandomPhone()));
		}

		for (Customer customer: list) {
			daoCustomer.insert(customer);
		}
	}

	private void employees() {
		daoEmployee.insert(new Employee("Administrador", "admin@email.org", "admin"));
	}

	private void destinations() {
		List<Destination> list = new ArrayList<Destination>();
		list.add(new Destination("Afogados da Ingazeira", 0.0f));
		list.add(new Destination("Baixa Grande do Ribeiro", 0.0f));
		list.add(new Destination("Biribal da Ximboca", 0.0f));
		list.add(new Destination("Braço do Trombudo", 0.0f));
		list.add(new Destination("Brejo das Freiras", 0.0f));
		list.add(new Destination("Buranhém Velho", 0.0f));
		list.add(new Destination("Cacha Pregos", 0.0f));
		list.add(new Destination("Corrego do Pirapora", 0.0f));
		list.add(new Destination("Derribadinha", 0.0f));
		list.add(new Destination("Entroncamento de Jampruca", 0.0f));
		list.add(new Destination("Nhecolândia", 0.0f));
		list.add(new Destination("Passo do Sabão", 0.0f));
		list.add(new Destination("Quilombo Catorze", 0.0f));
		list.add(new Destination("Ribeirão das Catilanga", 0.0f));
		list.add(new Destination("Santana do Tabuleiro", 0.0f));
		list.add(new Destination("São Catatau do Mato Dentro", 0.0f));
		list.add(new Destination("São José do Batatal", 0.0f));
		list.add(new Destination("Tanque D'Arca", 0.0f));
		list.add(new Destination("Trombudo Central", 0.0f));
		list.add(new Destination("Varre-Sai", 0.0f));

		for (Destination destination: list) {
			daoDestination.insert(destination);
		}
	}

	private void trips() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		DateTimeFunction function = new DateTimeFunction(
				DateTimeUtil.toCalendar("01/01/2010", sdf),
				DateTimeUtil.toCalendar("31/12/2014", sdf));

		List<Customer> customers = daoCustomer.find();
		List<Destination> destinations = daoDestination.find();

		List<Trip> list = new ArrayList<Trip>();
		for (Customer customer: customers) {
			int numTrips = random.nextInt(20);
			for (int i = 0; i < numTrips; i++) {
				list.add(new Trip(((Calendar) function.generateValue()).getTime(), random.nextInt(6),
						customer, destinations.get(random.nextInt(destinations.size()))));
			}
		}

		for (Trip trip: list) {
			daoTrip.insert(trip);
		}
	}
}
