package de.mohadipe.dynastie.ui.screens.external;

import com.google.inject.AbstractModule;

import de.mohadipe.dynastie.ui.screens.internal.MainMenu;
import de.mohadipe.dynastie.ui.screens.internal.MapSetupScreen;
import de.mohadipe.dynastie.ui.screens.internal.SpielSetupScreen;
import de.mohadipe.dynastie.ui.screens.internal.Splash;

public class ScreensModul extends AbstractModule {
    @Override
    protected void configure() {
        bind(de.mohadipe.dynastie.ui.screens.external.ISplash.class).to(Splash.class);
        bind(de.mohadipe.dynastie.ui.screens.external.IMainMenu.class).to(MainMenu.class);
        bind(de.mohadipe.dynastie.ui.screens.external.ISpielSetupScreen.class).to(SpielSetupScreen.class);
        bind(de.mohadipe.dynastie.ui.screens.external.IMapSetupScreen.class).to(MapSetupScreen.class);
    }
}
