package quikkoo.mt.xptotour.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.primefaces.event.RateEvent;

import quikkoo.mt.xptotour.business.TripBusiness;
import quikkoo.mt.xptotour.model.Customer;
import quikkoo.mt.xptotour.model.Trip;

@ManagedBean(name = "rating")
@ViewScoped
public class RatingController implements Serializable {

	private static final long serialVersionUID = 7883875733050704132L;

	private static final String MSG_RATING_TITLE  = "Avaliação";
	private static final String MSG_RATING_DETAIL = "Trip para %s avaliada com nota %d";

	@Inject
	private TripBusiness boTrip;

	private Customer customer;
	private List<Trip> trips;

	@PostConstruct
	public void construct() {
		customer = (Customer) AppController.session()
			.getAttribute(AppController.KEY_USER);
		trips = boTrip.tripsForCustomer(customer);
	}

	public Customer getCustomer() {
		return customer;
	}

	public List<Trip> getTrips() {
		return trips;
	}

	public void onRate(RateEvent event) {
		Trip trip = (Trip) event.getComponent()
				.getAttributes()
				.get("trip");

		try {
			boTrip.update(trip);

			AppController.addInfoMessage(MSG_RATING_TITLE, 
					String.format(MSG_RATING_DETAIL, trip.getDestination().getName(), trip.getRating()));
		}
		catch (IllegalArgumentException e) {
			AppController.addWarnMessage(null, e.getMessage());
		}
	}

	public void onCancel(Trip trip) {
		try {
			boTrip.update(trip);

			AppController.addInfoMessage(MSG_RATING_TITLE, 
					String.format(MSG_RATING_DETAIL, trip.getDestination().getName(), trip.getRating()));
		}
		catch (IllegalArgumentException e) {
			AppController.addWarnMessage(null, e.getMessage());
		}
	}
}
