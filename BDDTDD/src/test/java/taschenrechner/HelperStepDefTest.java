package taschenrechner;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

public class HelperStepDefTest {

	@Test
	public void zweiDrittel() {
		String zweiDrittel = "2/3";
		Bruch bruch = new HelperStepDef().wandleInBruch(zweiDrittel);
		Assert.assertThat(bruch.getZaehler().intValue(), Matchers.is(2));
		Assert.assertThat(bruch.getNenner().intValue(), Matchers.is(3));
	}
}
