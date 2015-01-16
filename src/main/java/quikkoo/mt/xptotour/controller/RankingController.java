package quikkoo.mt.xptotour.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import quikkoo.mt.xptotour.business.DestinationBusiness;
import quikkoo.mt.xptotour.model.Destination;

@ManagedBean(name = "ranking")
@ViewScoped
public class RankingController implements Serializable {

	private static final long serialVersionUID = 2255011834878282332L;

	@Inject
	private DestinationBusiness boDestination;

	@PostConstruct
	public void construct() {
		boDestination.calculeRating();
	}

	public List<Destination> getHighestDestinations() {
		return boDestination.getHighestRating();
	}

	public List<Destination> getLowestDestinations() {
		return boDestination.getLowestRating();
	}
}
