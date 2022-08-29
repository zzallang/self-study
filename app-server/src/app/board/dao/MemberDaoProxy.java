package app.board.dao;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import com.google.gson.Gson;
import app.board.domain.Member;

// MemberDao와 통신을 담당할 대행 객체
//
public class MemberDaoProxy{

  String dataName;
  DataInputStream in;
  DataOutputStream out;

  public MemberDaoProxy(String dataName, DataInputStream in, DataOutputStream out) {
    this.dataName = dataName;
    this.in = in;
    this.out = out;
  }

  public boolean insert(Member member) throws Exception {
    out.writeUTF(dataName); 
    out.writeUTF("insert"); 
    out.writeUTF(new Gson().toJson(member));
    return in.readUTF().equals("success");
  }

  public boolean update(Member member) throws Exception {
    out.writeUTF("update");
    out.writeUTF(new Gson().toJson(member));

    if (in.readUTF().equals("success")) {
      System.out.println("변경했습니다.");
    } else {
      System.out.println("변경에 실패했습니다.");
    } 
    return in.readUTF().equals("success");
  }

  public Member findByEmail(String email) throws Exception {
    out.writeUTF(dataName); 
    out.writeUTF("findByNo"); 

    if (in.readUTF().equals("fail")) {
      System.out.println("해당 번호의 게시글이 없습니다!");
      return null;
    }
    return new Gson().fromJson(in.readUTF(), Member.class);
  }

  public boolean delete(String email) throws Exception {
    out.writeUTF(dataName); 
    out.writeUTF("delete"); 
    return in.readUTF().equals("success");
  }

  public Member[] findAll() throws Exception {
    out.writeUTF(dataName);
    out.writeUTF("findAll");

    if (in.readUTF().equals("fail")) {
      return null;
    }
    return new Gson().fromJson(in.readUTF(), Member[].class);
  }
}
