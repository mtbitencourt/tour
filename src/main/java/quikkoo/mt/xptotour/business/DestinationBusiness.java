package quikkoo.mt.xptotour.business;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import quikkoo.mt.xptotour.model.Destination;
import quikkoo.mt.xptotour.model.Trip;
import quikkoo.mt.xptotour.repository.DestinationRepository;
import quikkoo.mt.xptotour.repository.TripRepository;

@Named
@RequestScoped
public class DestinationBusiness implements Serializable {

	private static final long serialVersionUID = 9070238936103582162L;

	private static final int MAX_RESULTS = 5;

	@Inject
	private DestinationRepository daoDestination;

	@Inject
	private TripRepository daoTrip;

	public DestinationBusiness() {
	}

	public DestinationBusiness(DestinationRepository daoDestination, TripRepository daoTrip) {
		this.daoDestination = daoDestination;
		this.daoTrip = daoTrip;
	}

	public List<Destination> getHighestRating() {
		return daoDestination.findHighestRating(MAX_RESULTS);
	}

	public List<Destination> getLowestRating() {
		return daoDestination.findLowestRating(MAX_RESULTS);
	}

	public void calculeRating() {
		List<Destination> destinations = daoDestination.find();
		for (Destination destination: destinations) {
			List<Trip> trips = daoTrip.findByDestination(destination); 

			if (trips.size() > 0) {
				int total = 0;
				for (Trip trip: trips) {
					total += trip.getRating();
				}
				float rating = (float) total / (float) trips.size();
				destination.setRating(rating);

				daoDestination.update(destination);
			}
		}
	}
}
