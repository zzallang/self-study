package app.board;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Hashtable;
import app.Servlet.Servlet;
import app.board.servlet.BoardServlet;
import app.board.servlet.MemberServlet;

public class ServerApp {

  public static void main(String[] args) {
    System.out.println("[게시글 데이터 관리 서버]");

    try (
        ServerSocket serverSocket = new ServerSocket(8888)) {

      System.out.println("서버 소켓 준비 완료!");

      // 클라이언트 요청을 처리할 객체 준비
      Hashtable<String,Servlet> servletMap = new Hashtable<>();
      servletMap.put("board", new BoardServlet("board"));
      servletMap.put("reading", new BoardServlet("reading"));
      servletMap.put("visit", new BoardServlet("visit"));
      servletMap.put("notice", new BoardServlet("notice"));
      servletMap.put("diary", new BoardServlet("diary"));
      servletMap.put("member", new MemberServlet("member"));

      while (true) {
        try (
            Socket socket = serverSocket.accept();
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {

          System.out.println("클라이언트 연결 완료");

          while (true) {
            // 클라이언트와 서버 사이에 정해진 규칙(protocol)에 따라 데이터를 주고 받는다.
            String dataName = in.readUTF();

            if (dataName.equals("exit")) {
              break; 
            }

            Servlet servlet = servletMap.get(dataName);
            if (servlet != null) {
              servlet.service(in, out);
            } else {
              out.writeUTF("fail");
            }
          }
          System.out.println("클라이언트 연결 종료");
        } // 안 쪽 try 
      }
    } catch (Exception e) {
      e.printStackTrace();
    } // 바깥 쪽 try

    System.out.println("서버 종료");
  }
}


