package quikkoo.mt.xptotour.model;

import java.io.Serializable;

import javax.persistence.Entity;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

@Entity
public class Employee extends User implements Serializable {

	private static final long serialVersionUID = 522018398779805780L;

	private String password;

	protected Employee() {
	}

	public Employee(String name, String email, String password) {
		super(name, email);
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public boolean equals(Object obj) {
		if (super.equals(obj)) {
			return true;
		}

		if (getClass() != obj.getClass()) {
			return false;
		}

		Employee other = (Employee) obj;
		return Objects.equal(id, other.id)
				&& Objects.equal(name, other.name)
				&& Objects.equal(email, other.email)
				&& Objects.equal(password, other.password);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(
				id,
				name,
				email,
				password);
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(Employee.class)
				.add("id", id)
				.add("name", name)
				.add("email", email)
				.add("password", password)
				.toString();
	}
}
