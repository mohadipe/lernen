package de.mohadipe.dynastie.bewegen;

import com.google.inject.Binder;
import com.google.inject.Module;

import de.mohadipe.dynastie.input.Input;

public class DummyBewegenBinder implements Module {

	private Class<? extends Input> bindingFuerInput;
	
	@Override
	public void configure(Binder binder) {
		binder.bind(Input.class).to(bindingFuerInput);
	}

	public void setBindingFuerInput(Class<? extends Input> bindingFuerInput) {
		this.bindingFuerInput = bindingFuerInput;
	}
}
