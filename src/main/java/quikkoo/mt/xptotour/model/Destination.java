package quikkoo.mt.xptotour.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

@Entity
public class Destination implements Serializable {

	private static final long serialVersionUID = 3072875948364966003L;

	private Long id;
	private String name;
	private Float rating;
	private List<Trip> trips;

	protected Destination() {
		this.trips = new LinkedList<Trip>();
	}

	public Destination(String name, Float rating) {
		this.name = name;
		this.rating = rating;
		this.trips = new LinkedList<Trip>();
	}

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getRating() {
		return rating;
	}

	public void setRating(Float rating) {
		this.rating = rating;
	}

	@OneToMany(mappedBy = "destination")
	public List<Trip> getTrips() {
		return trips;
	}

	public void setTrips(List<Trip> trips) {
		this.trips = trips;
	}

	@Override
	public boolean equals(Object obj) {
		if (super.equals(obj)) {
			return true;
		}

		if (getClass() != obj.getClass()) {
			return false;
		}

		Destination other = (Destination) obj;
		return Objects.equal(id, other.id)
				&& Objects.equal(name, other.name)
				&& Objects.equal(rating, other.rating);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(
				id,
				name,
				rating);
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(Destination.class)
				.add("id", id)
				.add("name", name)
				.add("rating", rating)
				.toString();
	}
}
