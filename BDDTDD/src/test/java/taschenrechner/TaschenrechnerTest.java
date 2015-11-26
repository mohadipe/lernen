package taschenrechner;

import org.junit.Assert;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class TaschenrechnerTest {

	ITaschenrechner rechner;
	
	public TaschenrechnerTest() {
		Injector injector = Guice.createInjector(new TaschenrechnerGuiceModul());
		rechner = injector.getInstance(ITaschenrechner.class);
	}
	
	@Test
	public void additionVonZweiZahlen() {
		int summand1 = 2;
		int summand2 = 3;
		
		int summe = rechner.addiere(summand1, summand2);
		
		Assert.assertEquals(5, summe);
	}
}
