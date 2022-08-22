package app.study;

import com.google.gson.Gson;
import app.board.domain.Board;

public class Test01 {

  public static void main(String[] args) {

    Board b = new Board();
    b.no = 100;
    b.title = "제목";
    b.content = "내용";
    b.writer = "임은지";
    b.password = "1111";
    b.viewCount = 111;
    b.createdDate = System.currentTimeMillis();

    // Object --> JSON 문자열
    Gson gson = new Gson();

    String json = gson.toJson(b); // gson의 객체를 주면 json 문자열을 만들어서 리턴
    System.out.println(json);

    Board b2 = gson.fromJson(json, Board.class); // 얘는 gson 메소드에서 만든 객체를 리턴
    System.out.println(b2);

    System.out.println(b==b2); // false

  }

}
