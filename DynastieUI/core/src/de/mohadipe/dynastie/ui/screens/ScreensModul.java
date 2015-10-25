package de.mohadipe.dynastie.ui.screens;

import com.google.inject.AbstractModule;

public class ScreensModul extends AbstractModule {
    @Override
    protected void configure() {
        bind(ISplash.class).to(Splash.class);
        bind(IMainMenu.class).to(MainMenu.class);
        bind(ISpielSetupScreen.class).to(SpielSetupScreen.class);
        bind(IMapSetupScreen.class).to(MapSetupScreen.class);
    }
}
