package org.example;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Scanner sc = new Scanner(System.in);

        List<Post> posts = new ArrayList<>();
        String command = "";
        int index = 1;
        String fileName = "data.dat";
        FileInputStream fis = new FileInputStream(fileName);
        ObjectInputStream ois = new ObjectInputStream(fis);
        posts = (List<Post>) ois.readObject();
        System.out.println("== 명언 앱 ==");
        while(true) {
            System.out.print("명령) ");
            command = sc.nextLine();
            if(command.equals("종료")) {
                FileOutputStream fos = new FileOutputStream(fileName);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(posts);
                break;
            }
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
            if(command.contains("수정?id=")) {
                int id = Integer.parseInt(command.substring(6,7));
                for(int i=0; i<posts.size(); i++) {
                    if(posts.get(i).getId() == id) {
                        System.out.printf("명언(기존) : %s\n", posts.get(i).getContent());
                        System.out.print("명언 : ");
                        posts.get(i).setContent(sc.nextLine());
                        System.out.printf("작가(기존) : %s\n", posts.get(i).getAuthor());
                        System.out.print("작가 : ");
                        posts.get(i).setAuthor(sc.nextLine());
                        break;
                    }
                    System.out.printf("%d번 명언이 존재하지 않습니다.\n", id);
                }
            }
            if(command.equals("빌드")) {
                JSONArray outer = new JSONArray();
                for(Post post : posts) {
                    JSONObject inner = new JSONObject();
                    inner.put("id", post.getId());
                    inner.put("content", post.getContent());
                    inner.put("author", post.getAuthor());
                    outer.add(inner);
                }

                String buildName = "data.json";
                FileWriter fileWriter = new FileWriter(buildName);
                fileWriter.write(outer.toJSONString());
                fileWriter.flush();
                fileWriter.close();

                System.out.printf("%s 파일의 내용이 갱신되었습니다.\n", buildName);
            }
        }
    }
}