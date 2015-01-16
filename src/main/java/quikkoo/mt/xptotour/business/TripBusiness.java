package quikkoo.mt.xptotour.business;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import quikkoo.mt.xptotour.model.Customer;
import quikkoo.mt.xptotour.model.Trip;
import quikkoo.mt.xptotour.repository.TripRepository;

@Named
@RequestScoped
public class TripBusiness {

	public static final int MIN_RATING = 0;
	public static final int MAX_RATING = 5;

	private static final String MSG_ERROR_RATING =
			"A avaliação de uma viagem deve estrar entre %d e %d";

	@Inject
	private TripRepository daoTrip;

	public TripBusiness() {
	}

	public TripBusiness(TripRepository daoTrip) {
		this.daoTrip = daoTrip;
	}

	public List<Trip> tripsForCustomer(Customer customer) {
		return daoTrip.findByCustomer(customer);
	}

	public void update(Trip trip) {
		if (trip.getRating() < MIN_RATING || trip.getRating() > MAX_RATING) {
			throw new IllegalArgumentException(String.format(MSG_ERROR_RATING, MIN_RATING, MAX_RATING));
		}

		daoTrip.update(trip);
	}
}
