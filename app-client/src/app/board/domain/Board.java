package app.board.domain;

import java.io.Serializable;

// java.io.Serializable 인터페이스 
// - 인스턴스를 통째로 입출력할 수 있도록 표시하는 용도
// - 인터페이스에 추상 메소드가 선언되어 있찌 않기 때문에 따로 메소드를 구현할 필요 없음

public class Board implements Serializable {
  //java.io.Serializable 인터페이스 구현
  // 즉, Board 클래스의 필드 값을 통째로 입출력할 수 있는 용도로

  private static final long serialVersionUID = 1L;
  // 인스턴스를 저장하고 읽을 때 클래스의 변화 여부를 검증하기 위해
  // 이 클래스에 대해 임의의 버전을 지정한다.

  public int no;
  public String title;
  public String content;
  public String writer;
  public String password;
  public int viewCount;
  public long createdDate;

  @Override
  public String toString() {
    return "Board [no=" + no + ", title=" + title + ", content=" + content + ", writer=" + writer
        + ", password=" + password + ", viewCount=" + viewCount 
        + ", createdDate=" + new java.sql.Date(createdDate) 
        + "]";
  }

  public static Board create(String csv) {
    String[] values = csv.split(",");

    Board board = new Board();
    board.no = Integer.parseInt(values[0]);
    board.title = values[1];
    board.content = values[2];
    board.writer = values[3];
    board.password = values[4];
    board.viewCount = Integer.parseInt(values[5]);
    board.createdDate = Long.parseLong(values[6]);

    return board;
  }

  public String toCsv() {
    return String.format("%d,%s,%s,%s,%s,%d,%d",
        this.no,
        this.title,
        this.content,
        this.writer,
        this.password,
        this.viewCount,
        this.createdDate);
  }
}
