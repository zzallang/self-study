/*
 * board 데이터 처리
 */
package app.board.servlet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import app.Servlet.Servlet;
import app.board.dao.BoardDao;

public class BoardServlet implements Servlet {

  // 게시글 목록을 관리할 객체 준비
  private BoardDao boardDao;

  public BoardServlet(String dataName) {
    //    boardDao = new BoardDao(filename);
    //
    //    try { 
    //      boardDao.load();
    //    } catch (Exception e) {
    //      System.out.printf("%s 파일 로딩 중 오류 발생! \n", filename);
    //      //      e.printStackTrace();
    //    }
  }

  @Override
  public void service(DataInputStream in, DataOutputStream out) {
    try {

      String command = in.readUTF();

      switch (command) {
        case "findAll":
          out.writeUTF("success");
          break;
        case "findByNo":
          out.writeUTF("success");
          break;
        case "insert":
          out.writeUTF("success");
          break;
        case "delete":
          out.writeUTF("success");
          break;
        case "update":
          out.writeUTF("success");
        default :
          out.writeUTF("fail");
          break;
      }
    } catch (Exception e) {
      throw new RuntimeException(e); // 던지는 걸 선언할 수 없을 때 슬쩍 넘기기
    }
  }
}




