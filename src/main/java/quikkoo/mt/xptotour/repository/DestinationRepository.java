package quikkoo.mt.xptotour.repository;

import java.io.Serializable;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import quikkoo.mt.xptotour.model.Destination;

@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class DestinationRepository extends JpaRepository<Destination, Long> implements Serializable {

	private static final long serialVersionUID = 5569306521552931626L;

	public DestinationRepository() {
		super(Destination.class);
	}

	@Override
	public List<Destination> find() {
		return createNamedQuery("Destination.find")
				.getResultList();
	}

	public List<Destination> findHighestRating(int maxResults) {
		return createNamedQuery("Destination.findHighestRating")
				.setMaxResults(maxResults)
				.getResultList();
	}

	public List<Destination> findLowestRating(int maxResults) {
		return createNamedQuery("Destination.findLowestRating")
				.setMaxResults(maxResults)
				.getResultList();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void erase() {
		createNamedQuery("Destination.erase").executeUpdate();
	}
}
