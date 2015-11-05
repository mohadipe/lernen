package de.mohadipe.dynastie;

import de.mohadipe.dynastie.sieg.CheckSieg;
import de.mohadipe.dynastie.sieg.Vernichtung;

public enum Siegbedingung {
	VERNICHTEN;

	public CheckSieg getCheckSiegInstance() {
		switch (this) {
		case VERNICHTEN:
			return new Vernichtung();
		default:
			throw new RuntimeException("Kein Checker fuer Siegbedingung definiert: " + this);
		}
	}

}
