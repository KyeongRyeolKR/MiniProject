package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Post> posts = new ArrayList<>();
        String command = "";
        int index = 1;
        System.out.println("== 명언 앱 ==");
        while(true) {
            System.out.print("명령) ");
            command = sc.nextLine();
            if(command.equals("종료")) break;
            if(command.equals("등록")) {
                System.out.print("명언 : ");
                String content = sc.nextLine();
                System.out.print("작가 : ");
                String author = sc.nextLine();
                posts.add(new Post(index, author, content));
                System.out.printf("%d번 명언이 등록되었습니다.\n", index);
                index++;
            }
            if(command.equals("목록")) {
                System.out.println("번호 / 작가 / 명언");
                System.out.println("----------------------");
                for(int i=posts.size()-1; i>=0; i--) {
                    System.out.printf("%d / %s / %s\n", posts.get(i).getId(), posts.get(i).getAuthor(), posts.get(i).getContent());
                }
            }
            if(command.contains("삭제?id=")) {
                int id = Integer.parseInt(command.substring(6,7));
                for(int i=0; i<posts.size(); i++) {
                    if(posts.get(i).getId() == id) {
                        posts.remove(i);
                        System.out.printf("%d번 명언이 삭제되었습니다.\n", id);
                        break;
                    }
                    System.out.printf("%d번 명언이 존재하지 않습니다.\n", id);
                }
            }
        }
    }
}