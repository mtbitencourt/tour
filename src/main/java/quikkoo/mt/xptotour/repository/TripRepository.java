package quikkoo.mt.xptotour.repository;

import java.io.Serializable;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import quikkoo.mt.xptotour.model.Customer;
import quikkoo.mt.xptotour.model.Destination;
import quikkoo.mt.xptotour.model.Trip;

@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class TripRepository extends JpaRepository<Trip, Long> implements Serializable {

	private static final long serialVersionUID = 7245201539669045287L;

	public TripRepository() {
		super(Trip.class);
	}

	@Override
	public List<Trip> find() {
		return createNamedQuery("Trip.find")
				.getResultList();
	}

	public List<Trip> findRated() {
		return createNamedQuery("Trip.findRated")
				.getResultList();
	}

	public List<Trip> findByCustomer(Customer customer) {
		return createNamedQuery("Trip.findByCustomer")
				.setParameter("customer", customer)
				.getResultList();
	}

	public List<Trip> findByDestination(Destination destination) {
		return createNamedQuery("Trip.findByDestination")
				.setParameter("destination", destination)
				.getResultList();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void erase() {
		createNamedQuery("Trip.erase").executeUpdate();
	}
}
