/*
 * 게시글 메뉴 처리 클래스
 */
package app.board.handler;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.sql.Date;
import java.text.SimpleDateFormat;
import com.google.gson.Gson;
import app.Handler.AbstractHandler;
import app.board.dao.BoardDao;
import app.board.domain.Board;
import app.util.Prompt;

public class BoardHandler extends AbstractHandler {

  private String dataName;
  private DataInputStream in;
  private DataOutputStream out;

  // 게시글 목록을 관리할 객체 준비
  private BoardDao boardDao;

  public BoardHandler(String dataName, DataInputStream in, DataOutputStream out) {
    super (new String[] {"목록","상세보기","등록","삭제","변경"});

    this.dataName = dataName;
    this.in = in;
    this.out = out;
  }

  @Override
  public void service(int menuNo) {
    try {
      switch (menuNo) {
        case 1: this.onList(); break;
        case 2: this.onDetail(); break;
        case 3: this.onInput(); break;
        case 4: this.onDelete(); break;
        case 5: this.onUpdate(); break;
      }
    } catch (Exception e) {
      throw new RuntimeException(e); // 던지는 걸 선언할 수 없을 때 슬쩍 넘기기
    }
  }

  private void onList() {
    try {
      out.writeUTF(dataName); // 데이터 이름과 함께
      out.writeUTF("findAll"); // 명령어를 보내고

      if (in.readUTF().equals("fail")) {
        System.out.println("목록을 가져오는데 실패했습니다.");
        return;
      }

      String json = in.readUTF();
      Board[] boards = new Gson().fromJson(json, Board[].class);

      SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

      System.out.println("번호 제목 조회수 작성자 등록일");

      for (Board board : boards) {
        Date date = new Date(board.createdDate);
        String dateStr = formatter.format(date); 
        System.out.printf("%d\t%s\t%d\t%s\t%s\n",
            board.no, board.title, board.viewCount, board.writer, dateStr);
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private void onDetail() {
    try {
      int boardNo = 0;
      while (true) {
        try {
          boardNo = Prompt.inputInt("조회할 게시글 번호? ");
          break;
        } catch (Exception ex) {
          System.out.println("입력 값이 옳지 않습니다!");
        }
      }

      out.writeUTF(dataName); // 데이터 이름과 함께
      out.writeUTF("findByNo"); // 명령어를 보내고
      out.writeInt(boardNo);

      if (in.readUTF().equals("fail")) {
        System.out.println("해당 번호의 게시글이 없습니다!");
        return;
      }

      String json = in.readUTF();
      Board board = new Gson().fromJson(json, Board.class);

      System.out.printf("번호: %d\n", board.no);
      System.out.printf("제목: %s\n", board.title);
      System.out.printf("내용: %s\n", board.content);
      System.out.printf("조회수: %d\n", board.viewCount);
      System.out.printf("작성자: %s\n", board.writer);
      Date date = new Date(board.createdDate);
      System.out.printf("등록일: %tY-%1$tm-%1$td %1$tH:%1$tM\n", date);

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private void onInput() throws Exception {
    try {
      Board board = new Board();

      board.title = Prompt.inputString("제목? ");
      board.content = Prompt.inputString("내용? ");
      board.writer = Prompt.inputString("작성자? ");
      board.password = Prompt.inputString("암호? ");
      board.viewCount = 0;
      board.createdDate = System.currentTimeMillis();

      out.writeUTF(dataName); // 데이터 이름과 함께
      out.writeUTF("insert"); // 명령어를 보내고
      String json = new Gson().toJson(board);
      out.writeUTF(json);

      if (in.readUTF().equals("success")) {
        System.out.println("게시글을 등록했습니다.");
      } else {
        System.out.println("게시글 등록에 실패했습니다.");
      }

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private void onDelete() throws Exception {
    try {
      int boardNo = 0;
      while (true) {
        try {
          boardNo = Prompt.inputInt("삭제할 게시글 번호? ");
          break;
        } catch (Exception ex) {
          System.out.println("입력 값이 옳지 않습니다!");
        }
      }

      out.writeUTF(dataName); // 데이터 이름과 함께
      out.writeUTF("delete"); // 명령어를 보내고
      out.writeInt(boardNo);

      if (in.readUTF().equals("success")) {
        System.out.println("삭제하였습니다.");
      } else {
        System.out.println("해당 번호의 게시글이 없습니다!");
      }

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private void onUpdate() throws Exception {
    try {
      int boardNo = 0;
      while (true) {
        try {
          boardNo = Prompt.inputInt("변경할 게시글 번호? ");
          break;
        } catch (Throwable ex) {
          System.out.println("입력 값이 옳지 않습니다!");
        }
      }

      // 변경할 게시글 가져오기
      out.writeUTF(dataName);
      out.writeUTF("findByNo");
      out.writeInt(boardNo);

      if (in.readUTF().equals("fail")) {
        System.out.println("해당 번호의 게시글이 없습니다!");
        return;
      }

      String json = in.readUTF();
      Board board = new Gson().fromJson(json, Board.class);

      board.title = Prompt.inputString("제목?(" + board.title + ") ");
      board.content= Prompt.inputString(String.format("내용?(%s) ", board.content));

      String input = Prompt.inputString("변경하시겠습니까?(y/n) ");
      if (input.equals("y")) {
        // 게시글 변경하기
        out.writeUTF(dataName);
        out.writeUTF("update");
        out.writeUTF(new Gson().toJson(board));

        if (in.readUTF().equals("success")) {
          System.out.println("변경했습니다.");
        } else {
          System.out.println("변경에 실패했습니다.");
        } 

      } else {
        System.out.println("변경을 취소했습니다.");
      }

      out.writeUTF(dataName); // 데이터 이름과 함께
      out.writeUTF("update"); // 명령어를 보내고

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}




