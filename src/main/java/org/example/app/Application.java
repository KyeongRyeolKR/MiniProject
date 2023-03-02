package org.example.app;

import org.example.app.controller.AppController;

public class Application {
    public static void main(String[] args) {
        Application app = new Application();
        app.startApp();
    }

    public void startApp() {
        AppController appController = new AppController();
        appController.start();
    }
}
