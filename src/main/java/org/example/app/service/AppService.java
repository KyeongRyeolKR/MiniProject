package org.example.app.service;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.example.app.entity.Post;
import org.example.app.exception.MenuType;
import org.example.app.exception.NoInputPostIndexException;
import org.example.app.exception.PostNotExistsException;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class AppService {
    Scanner sc;
    List<Post> posts;

    public AppService() {
        this.sc = new Scanner(System.in);
        this.posts = new LinkedList<>();
    }

    public void create() {
        // 최근 등록 순 정렬 (id 내림차순)
        posts = posts.stream()
                .sorted((e1, e2) -> e2.getId() - e1.getId())
                .collect(Collectors.toList());
        Post newPost = new Post();
        int newId = 0;
        if(posts.size() == 0) {
            newPost.setId(1);
            newId = 1;
        } else {
            newId = posts.get(0).getId() + 1;
        }
        newPost.setId(newId);
        while (newPost.getContent() == null) {
            System.out.print("명언 : ");
            newPost.setContent(sc.nextLine());
        }
        while (newPost.getAuthor() == null) {
            System.out.print("작가 : ");
            newPost.setAuthor(sc.nextLine());
        }
        posts.add(newPost);
        System.out.printf("%d번 명언이 등록되었습니다.\n", newPost.getId());
    }

    public void show() {
        // 최근 등록 순 정렬 (id 내림차순)
        posts = posts.stream()
                .sorted((e1, e2) -> e2.getId() - e1.getId())
                .collect(Collectors.toList());

        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");
        for (Post post : posts) {
            System.out.printf("%d / %s / %s\n", post.getId(), post.getAuthor(), post.getContent());
        }
    }

    public void modify(int id) throws NoInputPostIndexException, PostNotExistsException {
        if (id == 0) {
            throw new NoInputPostIndexException(MenuType.MODIFY);
        }
        for (Post post : posts) {
            if (post.getId() == id) {
                System.out.printf("명언(기존) : %s\n", post.getContent());
                System.out.print("명언 : ");
                post.setContent(sc.nextLine());

                System.out.printf("작가(기존) : %s\n", post.getAuthor());
                System.out.print("작가 : ");
                post.setAuthor(sc.nextLine());

                System.out.printf("%d번 명언이 수정되었습니다.\n", id);
                return;
            }
        }
        throw new PostNotExistsException(id);
    }

    public void remove(int id) throws PostNotExistsException, NoInputPostIndexException {
        if (id == 0) {
            throw new NoInputPostIndexException(MenuType.REMOVE);
        }
        for (int i=0; i<posts.size(); i++) {
            if (posts.get(i).getId() == id) {
                posts.remove(i);
                System.out.printf("%d번 명언이 삭제되었습니다.\n", id);
                return;
            }
        }
        throw new PostNotExistsException(id);
    }

    public void save() throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("data.dat"));
        out.writeObject(posts);
        out.close();
    }

    public void load() throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("data.dat"));
        posts = (List<Post>) in.readObject();
        in.close();
    }

    public void build() throws IOException {
        JSONArray outer = new JSONArray();
        for(Post post : posts) {
            JSONObject inner = new JSONObject();
            inner.put("id", post.getId());
            inner.put("content", post.getContent());
            inner.put("author", post.getAuthor());
            outer.add(inner);
        }

        String fileName = "data.json";
        FileWriter fileWriter = new FileWriter(fileName);
        fileWriter.write(outer.toJSONString());
        fileWriter.flush();
        fileWriter.close();

        System.out.printf("%s 파일의 내용이 갱신되었습니다.\n", fileName);
    }
}
