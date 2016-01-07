package de.mohadipe.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.google.gson.Gson;

@Entity
public class MasterEntity {

	@Id
	@GeneratedValue
	private Long id;
	@OneToMany
	private List<DetailEntity> details;
	
	public void addUser(DetailEntity u) {
		if (details == null){
			details = new ArrayList<DetailEntity>();
		}
		details.add(u);
	}

	public void removeUser(DetailEntity u) {
		details.remove(u);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((details == null) ? 0 : details.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		MasterEntity other = (MasterEntity) obj;
		if (details == null) {
			if (other.details != null)
				return false;
		} else if (!details.equals(other.details))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}

}
