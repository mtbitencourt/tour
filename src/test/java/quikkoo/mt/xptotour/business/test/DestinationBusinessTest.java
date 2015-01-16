package quikkoo.mt.xptotour.business.test;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import quikkoo.mt.xptotour.business.DestinationBusiness;
import quikkoo.mt.xptotour.model.Destination;
import quikkoo.mt.xptotour.model.Trip;
import quikkoo.mt.xptotour.repository.DestinationRepository;
import quikkoo.mt.xptotour.repository.TripRepository;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;

public class DestinationBusinessTest {

	@BeforeClass
	public static void beforeClass() {
		FixtureFactoryLoader.loadTemplates("quikkoo.mt.xptotour.templates");
	}

	@Test
	public void testCalculeRating() {
		final int NUM_DESTINATIONS = 5;
		final int NUM_TRIPS = 10;

		List<Destination> destinations = Fixture.from(Destination.class)
				.gimme(NUM_DESTINATIONS, "valid-witout-trips");

		List<List<Trip>> trips = new ArrayList<List<Trip>>();
		for (int i = 0; i < NUM_DESTINATIONS; i++) {
			List<Trip> tmp = Fixture.from(Trip.class)
					.gimme(NUM_TRIPS, "valid");
			trips.add(tmp);
		}

		DestinationRepository daoDestination = mock(DestinationRepository.class);
		doReturn(destinations).when(daoDestination)
				.find();
		doNothing().when(daoDestination)
				.update((any(Destination.class)));

		TripRepository daoTrip = mock(TripRepository.class);
		for (int i = 0; i < NUM_DESTINATIONS; i++) {
			doReturn(trips.get(i)).when(daoTrip)
				.findByDestination(any(Destination.class));
		}

		DestinationBusiness boDestination = new DestinationBusiness(daoDestination, daoTrip);
		boDestination.calculeRating();

		verify(daoDestination, times(1)).find();
		verify(daoDestination, times(NUM_DESTINATIONS)).update(any(Destination.class));
		verify(daoTrip, times(NUM_DESTINATIONS)).findByDestination(any(Destination.class));
	}
}
