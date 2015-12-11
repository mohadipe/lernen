package de.mohadipe.ui.test.robot;

import gherkin.deps.com.google.gson.Gson;

public class Koordinaten2D {
	public int x;
	public int y;
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
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
		Koordinaten2D other = (Koordinaten2D) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return new Gson().toJson(this).toString();
	}
}
