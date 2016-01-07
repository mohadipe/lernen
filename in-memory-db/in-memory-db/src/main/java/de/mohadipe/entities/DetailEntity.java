package de.mohadipe.entities;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.google.gson.Gson;

@Entity
public class DetailEntity {

	@Id
	@GeneratedValue
	private Long id;
	@Basic
	private String email;
	@Basic
	private String firstName;
	@Basic
	private String lastName;
	@Basic
	private String organisation;

	public void setEmail(String string) {
		this.email = string;
	}

	public void setFirstName(String string) {
		this.firstName = string;
	}

	public void setLastName(String string) {
		this.lastName = string;
	}

	public void setOrganisation(String string) {
		this.organisation = string;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result
				+ ((organisation == null) ? 0 : organisation.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DetailEntity other = (DetailEntity) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (organisation == null) {
			if (other.organisation != null)
				return false;
		} else if (!organisation.equals(other.organisation))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}

}
