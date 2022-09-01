/*
 * 회원 메뉴 처리 클래스
 */
package app.board.servlet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.lang.reflect.Member;
import com.google.gson.Gson;
import app.Servlet.Servlet;
import app.board.dao.MemberDao;

public class MemberServlet implements Servlet{

  private MemberDao memberDao;
  private String filename;

  public MemberServlet(String dataName) { // 생성자
    filename = dataName + ".josn";
    memberDao = new MemberDao(filename);

    try {
      memberDao.load();
    } catch (Exception e) {
      System.out.printf("%s 파일 로딩 중 오류 발생!\n", filename);
      e.printStackTrace();
    }
  }

  @Override
  public void service(DataInputStream in, DataOutputStream out) {
    try {

      String command = in.readUTF();
      String json = null;
      String email = null;
      Member member = null;

      switch (command) {
        case "findAll":
          Member[] members = memberDao.findAll();
          out.writeUTF(SUCCESS);
          out.writeUTF(new Gson().toJson(members));
          break;
        case "findByEmail":
          email = in.readUTF();
          member = memberDao.findByEmail(email);
          if (member != null) {
            out.writeUTF(SUCCESS);
            out.writeUTF(new Gson().toJson(member));
          } else {
            out.writeUTF(FAIL);
          }
          break;
        case "insert":
          json = in.readUTF();
          member = new Gson().fromJson(json, Member.class);
          memberDao.save();
          memberDao.insert(member);
          out.writeUTF(SUCCESS);
          break;
        case "update":
          json = in.readUTF();
          member = new Gson().fromJson(json, Member.class);
          if (memberDao.update(member)) {
            memberDao.save();
            out.writeUTF(SUCCESS);
          } else {
            out.writeUTF(FAIL);
          }
        case "delete":
          email = in.readUTF();
          if (memberDao.delete(email)) {
            memberDao.save();
            out.writeUTF(SUCCESS);
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




