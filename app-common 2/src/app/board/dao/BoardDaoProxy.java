package app.board.dao;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import com.google.gson.Gson;
import app.board.domain.Board;


// BoardDao와 통신을 담당할 대행 객체
//
public class BoardDaoProxy {

  String dataName;
  DataInputStream in;
  DataOutputStream out;

  public BoardDaoProxy(String dataName, DataInputStream in, DataOutputStream out) {
    this.dataName = dataName;
    this.in = in;
    this.out = out;
  }

  public boolean insert(Board board) throws Exception {
    out.writeUTF(dataName); // 데이터 이름과 함께
    out.writeUTF("insert"); // 명령어를 보내고
    out.writeUTF(new Gson().toJson(board));
    return in.readUTF().equals("success");
  }

  public boolean update(Board board) throws Exception {
    out.writeUTF("update");
    out.writeUTF(new Gson().toJson(board));
    return in.readUTF().equals("success");
  }

  public Board findByNo(int boardNo) throws Exception {
    out.writeUTF(dataName); // 데이터 이름과 함께
    out.writeUTF("findByNo"); // 명령어를 보내고
    out.writeInt(boardNo);

    if (in.readUTF().equals("fail")) {
      return null;
    }
    return new Gson().fromJson(in.readUTF(), Board.class);
  }

  public boolean delete(int boardNo) throws Exception {
    out.writeUTF(dataName); // 데이터 이름과 함께
    out.writeUTF("delete"); // 명령어를 보내고
    out.writeInt(boardNo);
    return in.readUTF().equals("success");
  }

  public Board[] findAll() throws Exception {
    out.writeUTF(dataName); // 데이터 이름과 함께
    out.writeUTF("findAll"); // 명령어를 보내고

    if (in.readUTF().equals("fail")) {
      return null;
    }
    return new Gson().fromJson(in.readUTF(), Board[].class);
  }
}
