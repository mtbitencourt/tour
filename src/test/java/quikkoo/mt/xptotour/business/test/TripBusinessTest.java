package quikkoo.mt.xptotour.business.test;

import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.BeforeClass;
import org.junit.Test;

import quikkoo.mt.xptotour.business.TripBusiness;
import quikkoo.mt.xptotour.model.Trip;
import quikkoo.mt.xptotour.repository.TripRepository;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;

public class TripBusinessTest {

	@BeforeClass
	public static void beforeClass() {
		FixtureFactoryLoader.loadTemplates("quikkoo.mt.xptotour.templates");
	}

	@Test
	public void testUpdateTripRating() {
		Trip trip = Fixture.from(Trip.class)
				.gimme("valid");

		TripRepository daoTrip = mock(TripRepository.class);
		doNothing().when(daoTrip)
				.update(any(Trip.class));

		TripBusiness boTrip = new TripBusiness(daoTrip);
		boTrip.update(trip);

		verify(daoTrip, times(1)).update(trip);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testUpdateTripInvalidWithRatingLessThan0() {
		Trip trip = Fixture.from(Trip.class).gimme("invalid-rating-less-then-0");

		TripRepository daoTrip = mock(TripRepository.class);
		doNothing().when(daoTrip)
				.update(any(Trip.class));

		TripBusiness boTrip = new TripBusiness(daoTrip);
		boTrip.update(trip);

		verify(daoTrip, times(0)).update(trip);

		fail("Salvar viagem com avaliação menor que 0 deve lançar exceção");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testUpdateTripInvalidWithRatingGreatherThan5() {
		Trip trip = Fixture.from(Trip.class).gimme("invalid-rating-greather-then-5");

		TripRepository daoTrip = mock(TripRepository.class);
		doNothing().when(daoTrip)
				.update(any(Trip.class));

		TripBusiness boTrip = new TripBusiness(daoTrip);
		boTrip.update(trip);

		verify(daoTrip, times(0)).update(trip);

		fail("Salvar viagem com avaliação maior que 5 deve lançar exceção");
	}
}
