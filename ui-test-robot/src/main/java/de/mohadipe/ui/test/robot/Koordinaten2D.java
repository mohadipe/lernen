package de.mohadipe.ui.test.robot;

import com.google.gson.Gson;


public class Koordinaten2D {
	public int x;
	public int y;
	public Koordinaten2D(int x2, int y2) {
		this.x = x2;
		this.y = y2;
	}
	public Koordinaten2D() {
	}
	public boolean isNachbarVon(Koordinaten2D kandidat) {
		if (x == kandidat.x && (y + 1) == kandidat.y) {
			return true;
		}
		if ((x+1) == kandidat.x && y == kandidat.y) {
			return true;
		}
		if ((x+1) == kandidat.x && (y+1) == kandidat.y) {
			return true;
		}
		if ((x+1) == kandidat.x && (y-1) == kandidat.y) {
			return true;
		}
		if ((x-1) == kandidat.x && (y+1) == kandidat.y) {
			return true;
		}
		if ((x-1) == kandidat.x && y == kandidat.y) {
			return true;
		}
		if (x == kandidat.x && (y-1) == kandidat.y) {
			return true;
		}
		if ((x-1) == kandidat.x && (y-1) == kandidat.y) {
			return true;
		}
		return false;
	}
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
