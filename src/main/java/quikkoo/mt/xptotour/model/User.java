package quikkoo.mt.xptotour.model;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class User implements Serializable {

	private static final long serialVersionUID = 6598548103903058566L;

	protected Long id;
	protected String name;
	protected String email;

	protected User() {
	}

	public User(String name, String email) {
		this.name = name;
		this.email = email;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
