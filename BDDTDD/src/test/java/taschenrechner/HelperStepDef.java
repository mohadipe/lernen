package taschenrechner;

import java.math.BigInteger;

public class HelperStepDef {

	private static final String TRENNER = "/";

	public Bruch wandleInBruch(String stringBruch) {
		if (stringBruch.indexOf(TRENNER) == -1) {
			BigInteger zaehlerNenner = new BigInteger(stringBruch);
			return new Bruch(zaehlerNenner, zaehlerNenner);
		}
		String zaehler = stringBruch.substring(0, stringBruch.indexOf(TRENNER));
		String nenner = stringBruch.substring(stringBruch.indexOf(TRENNER)+1);
		return new Bruch(zaehler, nenner);
	}

}
