package taschenrechner;

import java.math.BigInteger;

import com.google.gson.Gson;

public class Bruch {

	private BigInteger zaehler;
	private BigInteger nenner;

	public Bruch(String zaehler, String nenner) {
		this.zaehler = new BigInteger(zaehler);
		this.nenner = new BigInteger(nenner);
	}

	public Bruch(BigInteger zaehler, BigInteger nenner) {
		this.zaehler = zaehler;
		this.nenner = nenner;
	}

	public BigInteger getZaehler() {
		return zaehler;
	}

	public BigInteger getNenner() {
		return nenner;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nenner == null) ? 0 : nenner.hashCode());
		result = prime * result + ((zaehler == null) ? 0 : zaehler.hashCode());
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
		Bruch other = (Bruch) obj;
		if (nenner == null) {
			if (other.nenner != null)
				return false;
		} else if (!nenner.equals(other.nenner))
			return false;
		if (zaehler == null) {
			if (other.zaehler != null)
				return false;
		} else if (!zaehler.equals(other.zaehler))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}
}
