package de.mohadipe.dynastie.sieg;

import de.mohadipe.dynastie.Siegbedingung;

public final class CheckSiegBedingungen {

	public static CheckSieg getInstance(Siegbedingung siegbedingung) {
		return siegbedingung.getCheckSiegInstance();
	}

}
