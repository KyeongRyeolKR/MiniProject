package org.example.app.controller;

import org.example.app.exception.NoInputPostIndexException;
import org.example.app.exception.PostNotExistsException;
import org.example.app.service.AppService;

import java.io.IOException;
import java.util.Scanner;

public class AppController {
    Scanner sc;
    AppService appService;

    public AppController() {
        this.sc = new Scanner(System.in);
        this.appService = new AppService();
    }

    public void start() {
        System.out.println("== 명언 앱 ==");
        try { appService.load(); }
        catch (IOException | ClassNotFoundException ignored) {}
        while (true) {
            String menu_ = selectMenu();
            String menu = "";
            int id = 0;
            if (menu_.contains("id=")) {
                String[] s = menu_.split("id=");
                menu = s[0].substring(0, 2);
                id = Integer.parseInt(s[1]);
            } else {
                menu = menu_;
            }
            switch (menu) {
                case "등록":
                    appService.create();
                    break;
                case "목록":
                    appService.show();
                    break;
                case "수정":
                    try { appService.modify(id); }
                    catch (NoInputPostIndexException | PostNotExistsException ignored) {}
                    break;
                case "삭제":
                    try { appService.remove(id); }
                    catch (NoInputPostIndexException | PostNotExistsException ignored) {}
                    break;
                case "빌드":
                    try { appService.build(); }
                    catch (IOException e) {throw new RuntimeException(e);}
                    break;
                case "종료":
                    try { appService.save(); }
                    catch (IOException ignored) {}
                    System.out.println("프로그램을 종료합니다.");
                    System.exit(0);
            }
        }
    }

    public String selectMenu() {
        System.out.print("명령) ");
        return sc.nextLine();
    }
}
