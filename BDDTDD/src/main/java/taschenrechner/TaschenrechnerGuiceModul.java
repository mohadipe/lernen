package taschenrechner;

import com.google.inject.AbstractModule;

public class TaschenrechnerGuiceModul extends AbstractModule {

	@Override
	protected void configure() {
		bind(ITaschenrechner.class).to(Taschenrechner.class);
	}

}
