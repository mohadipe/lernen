package de.mohadipe.dynastie.workflow;

import junit.framework.Assert;

import org.junit.Test;

import de.mohadipe.dynastie.input.DummyKonfigurationsMenu;
import de.mohadipe.dynastie.input.DummyStartMenu;
import de.mohadipe.dynastie.sieg.SpielKonfiguration;

public class WorkflowSchritteKonfigurationTest {

	@Test
	public void reihenfolgeEinhalten() {
		WorkflowSchritteKonfiguration workflowSchritteKonfiguration = new WorkflowSchritteKonfiguration();
		workflowSchritteKonfiguration.addSchritt(1, new StartMenu(new DummyStartMenu(10)));
		workflowSchritteKonfiguration.addSchritt(10, new KonfigurationsMenu(new SpielKonfiguration(), new DummyKonfigurationsMenu(999)));
		workflowSchritteKonfiguration.addSchritt(999, new UnbekannterSchritt());

		Schritt actual = workflowSchritteKonfiguration.getSchritt();
		Assert.assertEquals(StartMenu.class, actual.getClass());
		workflowSchritteKonfiguration.setAktuellerSchritt(10);
		actual = workflowSchritteKonfiguration.getSchritt();
		Assert.assertEquals(KonfigurationsMenu.class, actual.getClass());
		workflowSchritteKonfiguration.setAktuellerSchritt(999);
		actual = workflowSchritteKonfiguration.getSchritt();
		Assert.assertEquals(UnbekannterSchritt.class, actual.getClass());
	}
}
