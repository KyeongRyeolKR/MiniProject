package org.example.app.exception;

public class NoInputPostIndexException extends Exception {

    public NoInputPostIndexException() {}

    public NoInputPostIndexException(MenuType menuType) {
        menuType.getMessage();
    }
}
