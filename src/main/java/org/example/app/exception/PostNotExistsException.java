package org.example.app.exception;

public class PostNotExistsException extends Exception {

    public PostNotExistsException() {}

    public PostNotExistsException(int id) {
        this("[ERROR] : " + id + "번 명언은 존재하지 않습니다.");
    }

    public PostNotExistsException(String message) {
        super(message);
        System.out.println(super.getMessage());
    }
}
