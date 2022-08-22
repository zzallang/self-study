/*
 * board 데이터 처리
 */
package app.board.servlet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import com.google.gson.Gson;
import app.Servlet.Servlet;
import app.board.dao.BoardDao;
import app.board.domain.Board;

public class BoardServlet implements Servlet {

  // 게시글 목록을 관리할 객체 준비
  private BoardDao boardDao;
  private String filename;

  public BoardServlet(String dataName) {
    filename = dataName + ".josn";
    boardDao = new BoardDao(filename);

    try {
      boardDao.load();
    } catch (Exception e) {
      System.out.printf("%s 파일 로딩 중 오류 발생! \n", filename);
      e.printStackTrace();
    }
  }

  @Override
  public void service(DataInputStream in, DataOutputStream out) {
    try {

      String command = in.readUTF();
      String json = null;
      Board board = null;
      int no = 0;

      switch (command) {
        case "findAll":
          Board[] boards = boardDao.findAll();
          out.writeUTF(SUCESSS);
          out.writeUTF(new Gson().toJson(boards));
          break;
        case "findByNo":
          no = in.readInt();
          board = boardDao.findByNo(no);
          if (board != null) {
            out.writeUTF(SUCESSS);
            out.writeUTF(new Gson().toJson(board));
          } else {
            out.writeUTF(FAIL);
          }
          break;
        case "insert":
          json = in.readUTF();
          board = new Gson().fromJson(json, Board.class);
          boardDao.save();
          boardDao.insert(board);
          out.writeUTF(SUCESSS);
          break;
        case "update":
          json = in.readUTF();
          board = new Gson().fromJson(json, Board.class);
          if (boardDao.update(board)) {
            boardDao.save();
            out.writeUTF(SUCESSS);
          } else {
            out.writeUTF(FAIL);
          }
        case "delete":
          no = in.readInt();
          if (boardDao.delete(no)) {
            boardDao.save();
            out.writeUTF(SUCESSS);
          } else {
            out.writeUTF(FAIL); 
          }
          break;
        default :
          out.writeUTF(FAIL);
          break;
      }
    } catch (Exception e) {
      throw new RuntimeException(e); // 던지는 걸 선언할 수 없을 때 슬쩍 넘기기
    }
  }
}




