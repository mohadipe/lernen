package de.mohadipe.dynastie.bewegen;

import com.google.inject.Binder;
import com.google.inject.Module;

import de.mohadipe.dynastie.input.Input;
import de.mohadipe.dynastie.input.SysoBewegungZielKoordinaten;

public class BewegenInjectBinder implements Module {

	@Override
	public void configure(Binder binder) {
		binder.bind(Input.class).to(SysoBewegungZielKoordinaten.class);
	}

}
