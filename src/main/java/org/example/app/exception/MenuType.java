package org.example.app.exception;

public enum MenuType {
    REMOVE("[ERROR] : 삭제할 번호를 입력해주세요. ex) 삭제?id=1"),
    MODIFY("[ERROR] : 수정할 번호를 입력해주세요. ex) 수정?id=1");

    private final String message;
    MenuType(String message) {
        this.message = message;
    }

    public void getMessage() {
        System.out.println(message);
    }

}
