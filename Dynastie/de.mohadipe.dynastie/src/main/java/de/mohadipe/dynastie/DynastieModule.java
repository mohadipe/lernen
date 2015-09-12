package de.mohadipe.dynastie;

import com.google.inject.Binder;
import com.google.inject.Module;

import de.mohadipe.dynastie.workflow.WorkflowKonfiguration;
import de.mohadipe.dynastie.workflow.WorkflowSchritteKonfiguration;

public class DynastieModule implements Module {

	@Override
	public void configure(Binder binder) {
		binder.bind(RandomService.class).to(RandomServiceImpl.class);
		binder.bind(Konfiguration.class).to(SpielKonfiguration.class);
		binder.bind(WorkflowKonfiguration.class).to(WorkflowSchritteKonfiguration.class);
	}

}
