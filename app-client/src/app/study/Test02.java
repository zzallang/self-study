package app.study;

import com.google.gson.Gson;
import app.board.domain.Board;

public class Test02 {

  public static void main(String[] args) {

    Board[] arr = new Board[3];

    Board b = new Board();
    b.no = 101;
    b.title = "제목1";
    b.content = "내용1";
    b.writer = "임은지1";
    b.password = "1111";
    b.viewCount = 111;
    b.createdDate = System.currentTimeMillis();
    arr[0] = b; // 보드 객체를 만들고 값을 넣은 후에 보드 객체 주소를 arr 0번째 주소에 넣기

    b = new Board();
    b.no = 102;
    b.title = "제목2";
    b.content = "내용2";
    b.writer = "임은지2";
    b.password = "1111";
    b.viewCount = 222;
    b.createdDate = System.currentTimeMillis();
    arr[1] = b;


    b = new Board();
    b.no = 103;
    b.title = "제목3";
    b.content = "내용3";
    b.writer = "임은지3";
    b.password = "1111";
    b.viewCount = 333;
    b.createdDate = System.currentTimeMillis();
    arr[2] = b;

    // 배열 --> JSON 문자열
    Gson gson = new Gson();

    String json = gson.toJson(arr); // 배열 주소를 주기
    System.out.println(json);


    // JSON --> 배열
    Board[] arr2 = gson.fromJson(json, Board[].class);
    for (Board e : arr2) {
      System.out.println(e);
    }

    System.out.println(arr == arr2); // false 새로 만든 객체이기 때문
  }

}
