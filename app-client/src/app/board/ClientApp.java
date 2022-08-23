package app.board;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Stack;
import app.Handler.Handler;
import app.board.handler.BoardHandler;
import app.board.handler.MemberHandler;
import app.util.Prompt;

public class ClientApp {

  // breadcrumb 메뉴를 저장할 스택 준비
  public static Stack<String> breadcrumbMenu = new Stack<>();

  public static void main(String[] args) {
    System.out.println("[게시글 관리 클라이언트]");

    try (
        // 네트워크 준비
        // => 정상적으로 연결 되었으면 Socket 객체를 리턴
        Socket socket = new Socket("127.0.0.1",8888);
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        DataInputStream in = new DataInputStream(socket.getInputStream())) {

      System.out.println("연결 완료");

      welcome();

      // 핸들러를 담을 레퍼런스 배열을 준비한다.
      Handler[] handlers = new Handler[] {
          new BoardHandler("board", in, out),
          new BoardHandler("reading", in, out),
          new BoardHandler("visit", in, out),
          new BoardHandler("notice", in, out),
          new BoardHandler("diary", in, out),
          new MemberHandler("member", in, out)
      };

      // "메인" 메뉴의 이름을 스택에 등록한다.
      breadcrumbMenu.push("메인");

      String[] menus = {"게시판","독서록","방명록","공지사항","일기장","회원"};

      loop: while (true) {

        // 메인 메뉴 출력
        printTitle();
        printMenus(menus);
        System.out.println();

        try {
          int mainMenuNo = Prompt.inputInt("메뉴를 선택하세요[1..6](0: 종료) ");
          System.out.println();

          if (mainMenuNo < 0 || mainMenuNo > menus.length) {
            System.out.println("메뉴 번호가 옳지 않습니다!");
            continue;
          } else if (mainMenuNo == 0) {
            out.writeUTF("exit");
            break loop;
          }

          // 메뉴에 진입할 때 breadcrumb 메뉴바에 해당 메뉴를 등록한다.
          breadcrumbMenu.push(menus[mainMenuNo - 1]);

          // 메뉴 번호로 Handler 레퍼런스에 들어있는 객체를 찾아 실행한다.
          handlers[mainMenuNo - 1].execute();

          breadcrumbMenu.pop();

        } catch (Exception ex) {
          System.out.println("입력 값이 옳지 않습니다.");
        }
      } // while

      Prompt.close();

      System.out.println("연결 종료");

    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println("종료!");
  }

  static void welcome() {
    System.out.println("[게시판 애플리케이션]");
    System.out.println();
    System.out.println("환영합니다!");
    System.out.println();
  }

  static void printMenus(String[] menus) {
    for (int i = 0; i < menus.length; i++) {
      System.out.printf("  %d: %s\n", i + 1, menus[i]);
    }
  }

  protected static void printTitle() {
    StringBuilder builder = new StringBuilder();
    for (String title : breadcrumbMenu) {
      if (!builder.isEmpty()) {
        builder.append(" > ");
      }
      builder.append(title);
    }
    System.out.printf("%s:\n", builder.toString());
  }

}
