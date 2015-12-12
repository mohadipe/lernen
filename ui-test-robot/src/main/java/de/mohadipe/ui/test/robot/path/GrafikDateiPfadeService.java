package de.mohadipe.ui.test.robot.path;

public class GrafikDateiPfadeService {
	private final boolean isInIDE;
	private String programmRoot;

	public GrafikDateiPfadeService(final boolean isInIDE) {
			this.isInIDE = isInIDE;
	}
	
	protected String getPathForIDE() {
		return "C:\\Users\\Mohadipe\\workspace\\lernen\\ui-test-robot\\grafiken\\";
	}

	protected String getPathForRuntime() {
		return programmRoot + "\\grafiken\\";
	}

	public String getPath() {
		if (isInIDE) {
			return getPathForIDE();
		}
		return getPathForRuntime();
	}

	public void setProgrammRoot(String programmRootVerzeichnis) {
		this.programmRoot = programmRootVerzeichnis;
	}
}
