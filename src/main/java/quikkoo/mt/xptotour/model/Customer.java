package quikkoo.mt.xptotour.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

@Entity
public class Customer extends User implements Serializable {

	private static final long serialVersionUID = -2380719942874283421L;

	private String cpf;
	private String phone;
	private List<Trip> trips;

	protected Customer() {
		this.trips = new LinkedList<Trip>();
	}

	public Customer(String name, String email, String cpf, String phone) {
		super(name, email);
		this.cpf = cpf;
		this.phone = phone;
		this.trips = new LinkedList<Trip>();
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@OneToMany(mappedBy = "customer")
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

		Customer other = (Customer) obj;
		return Objects.equal(id, other.id)
				&& Objects.equal(name, other.name)
				&& Objects.equal(email, other.email)
				&& Objects.equal(cpf, other.cpf)
				&& Objects.equal(phone, other.phone);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(
				id,
				name,
				email,
				cpf,
				phone);
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(Customer.class)
				.add("id", id)
				.add("name", name)
				.add("email", email)
				.add("cpf", cpf)
				.add("phone", phone)
				.toString();
	}
}
