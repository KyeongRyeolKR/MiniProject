package org.example.app;

import org.example.app.ui.AppMenu;

public class Application {
    public static void main(String[] args) {
        Application app = new Application();
        app.startApp();
    }

    public void startApp() {
        AppMenu appMenu = new AppMenu();
        appMenu.start();
    }
}
