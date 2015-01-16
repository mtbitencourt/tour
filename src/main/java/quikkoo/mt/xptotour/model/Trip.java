package quikkoo.mt.xptotour.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

@Entity
public class Trip implements Serializable {

	private static final long serialVersionUID = 389999580065800038L;

	private Long id;
	private Date date;
	private Integer rating;
	private Customer customer;
	private Destination destination;

	protected Trip() {
	}

	public Trip(Date date, Integer rating, Customer customer, Destination destination) {
		this.date = date;
		this.rating = rating;
		this.customer = customer;
		this.destination = destination;
	}

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Temporal(TemporalType.DATE)
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	@ManyToOne
	@JoinColumn(name = "customer_id", referencedColumnName = "id")
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@ManyToOne
	@JoinColumn(name = "destination_id", referencedColumnName = "id")
	public Destination getDestination() {
		return destination;
	}

	public void setDestination(Destination destination) {
		this.destination = destination;
	}

	@Override
	public boolean equals(Object obj) {
		if (super.equals(obj)) {
			return true;
		}

		if (getClass() != obj.getClass()) {
			return false;
		}

		Trip other = (Trip) obj;
		return Objects.equal(id, other.id)
				&& Objects.equal(date, other.date)
				&& Objects.equal(rating, other.rating)
				&& Objects.equal(customer, other.customer)
				&& Objects.equal(destination, other.destination);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(
				id,
				date,
				rating,
				customer,
				destination);
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(Trip.class)
				.add("id", id)
				.add("date", date)
				.add("rating", rating)
				.add("customer", customer)
				.add("destination", destination)
				.toString();
	}
}
