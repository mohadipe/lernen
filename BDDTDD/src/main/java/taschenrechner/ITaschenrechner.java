package taschenrechner;

import java.math.BigInteger;
import java.util.List;

public interface ITaschenrechner {

	int addiere(int summand1, int summand2);

	BigInteger addiere(List<BigInteger> summanden);

	Bruch addiereBrueche(List<Bruch> brueche);

}
