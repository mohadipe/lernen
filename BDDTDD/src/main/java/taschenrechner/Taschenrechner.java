package taschenrechner;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Taschenrechner implements ITaschenrechner {

	@Override
	public int addiere(int summand1, int summand2) {
		List<BigInteger> summanden = new ArrayList<BigInteger>();
		summanden.add(BigInteger.valueOf(summand1));
		summanden.add(BigInteger.valueOf(summand2));
		return addiere(summanden).intValue();
	}

	@Override
	public BigInteger addiere(List<BigInteger> summanden) {
		BigInteger summe = BigInteger.ZERO;
		for (BigInteger summand : summanden) {
			summe = summe.add(summand);
		}
		return summe;
	}

}
