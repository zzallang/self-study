package app.board.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import com.google.gson.Gson;
import app.board.domain.Board;


// 게시글 목록을 관리하는 역할
//
public class BoardDao {

  List<Board> list = new LinkedList<>();
  private int boardNo = 0;
  String filename;

  public BoardDao(String filename) {
    this.filename = filename;
  }

  public void load() throws Exception {
    try (BufferedReader in = new BufferedReader(new FileReader(filename))) {

      // 파일에서 JSON 문자열을 모두 읽어 StringBuilder에 담는다.
      StringBuilder strBuilder = new StringBuilder();
      String str;
      while ((str = in.readLine()) != null) {
        strBuilder.append(str);
      }

      // StringBuilder에 보관된 JSON 문자열을 가지고 Board[] 을 생성한다. 
      Board[] arr = new Gson().fromJson(strBuilder.toString(), Board[].class);

      // Board[] 배열의 저장된 객체를 List 로 옮긴다.
      for (int i = 0; i < arr.length; i++) {
        list.add(arr[i]);
      }

      // 게시글 데이터를 로딩한 후 마지막 게시글 번호를 설정해 둔다.
      boardNo = arr[arr.length - 1].no;
    }
  }

  public void save() throws Exception {
    try (FileWriter out = new FileWriter(filename)) {
      Board[] boards = list.toArray(new Board[0]); // list에서 Board 배열을 리턴받고 list에 들어있는 만큼만 뽑아서 boards에 저장
      out.write(new Gson().toJson(boards)); // 배열을 주면서 Json 문자열로 만든다.
      // 인스턴스 변수가 현재 다루는 Json 정보를 저장하기 때문에 각각 다른 gson 객체를 만들어야 함
    }
  }

  public void insert(Board board) {
    board.no = nextNo();
    list.add(board);
  }

  public boolean update(Board board) {
    for (int i = 0; i < list.size(); i++) {
      Board b= list.get(i);
      if (b.no == board.no) {
        list.set(i, board);
        return true;
      }
    }
    return false;
  }

  public Board findByNo(int boardNo) {
    for (int i = 0; i < list.size(); i++) {
      Board board = list.get(i);
      if (board.no == boardNo) {
        return board;
      }
    }
    return null;
  }

  public boolean delete(int boardNo) {
    for (int i = 0; i < list.size(); i++) {
      Board board = list.get(i);
      if (board.no == boardNo) {
        return list.remove(i) != null;
      }
    }
    return false;
  }

  public Board[] findAll() {

    // 목록에서 값을 꺼내는 일을 할 객체를 준비한다.
    Iterator<Board> iterator = list.iterator();

    // 역순으로 정렬하여 리턴한다.
    Board[] arr = new Board[list.size()];

    int index = list.size() - 1;
    while (iterator.hasNext()) {
      arr[index--] = iterator.next();
    }
    return arr;
  }

  private int nextNo() {
    return ++boardNo;
  }
}
