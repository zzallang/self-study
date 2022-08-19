/*
 * 게시판 관리 애플리케이션
 * 비트캠프-20220704
 */
package app.board;

import java.util.Stack;
import app.Handler.Handler;
import app.board.handler.BoardHandler;
import app.board.handler.MemberHandler;
import app.util.Prompt;

public class App {

  // breadcrumb 메뉴를 저장할 스택 준비
  public static Stack<String> breadcrumbMenu = new Stack<>(); // 다른 곳에서도 사용할 수 있게 publc으로 선언


  public static void main(String[] args) {
    try {
      welcome();

      // 핸들러를 담을 레퍼런스 배열을 준비한다.
      Handler[] handlers = new Handler[] {
          new BoardHandler("board.csv"),
          new BoardHandler("reading.csv"),
          new BoardHandler("visit.csv"),
          new BoardHandler("notice.csv"),
          new BoardHandler("diary.csv"),
          new MemberHandler("member.csv"),
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
    } catch (Exception e) {
      // 더 이상 애플리케이션을 실행할 수 없을 상황일 때
      // (main()메소드 까지 예외 보고가 올라 왔다는 것은 계속 실행할 수 없는 상태임)
      // 사용자에게 간단한 예외 메시지를 남기고 필요하다면 로그 파일에 오류 기록을 남기고 실행을 종료한다.

      System.out.printf("실행 오류 발생! - %s:%s\n",
          e.getClass().getName(), // 오류가 발생한 클래스의 정보를 가져와 이름을 출력 
          e.getMessage() != null ? e.getMessage() : ""); // 클래스의 간단한 오류에 대한 메시지 null이 아닐 경우만 출력 그 밖에는 빈 문자열
    } // catch 

    System.out.println("안녕히 가세요!");
  } // main

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
    for (String title : App.breadcrumbMenu) {
      if (!builder.isEmpty()) {
        builder.append(" > ");
      }
      builder.append(title);
    }
    System.out.printf("%s:\n", builder.toString());
  }
}







