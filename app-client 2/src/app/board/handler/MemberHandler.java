/*
 * 회원 메뉴 처리 클래스
 */
package app.board.handler;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.Date;
import app.Handler.AbstractHandler;
import app.board.dao.MemberDaoProxy;
import app.board.domain.Member;
import app.util.Prompt;

public class MemberHandler extends AbstractHandler{

  MemberDaoProxy memberDao;

  public MemberHandler(String dataName, DataInputStream in, DataOutputStream out) { // 생성자
    super (new String[] {"목록","상세보기","등록","삭제","변경"});

    memberDao = new MemberDaoProxy(dataName, in, out);
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

  private void onList() throws Exception {
    Member[] members = memberDao.findAll();

    if (members == null) {
      System.out.println("목록을 가져오는데 실패했습니다.");
      return;
    }

    System.out.println("이메일 이름");

    for (Member member : members) {
      System.out.printf("%s\t%s\n",
          member.email, member.name);
    }
  }

  private void onDetail() throws Exception {
    String email = Prompt.inputString("조회할 회원 이메일? ");
    while (true) {
      try {
        break;
      } catch (Exception ex) {
        System.out.println("입력 값이 옳지 않습니다!");
      }

      Member member = memberDao.findByEmail(email);

      if (member == null) {
        System.out.println("해당 이메일의 회원이 없습니다!");
        return;
      }

      System.out.printf("이름: %s\n", member.name);
      System.out.printf("이메일: %s\n", member.email);
      Date date = new Date(member.createdDate);
      System.out.printf("등록일: %tY-%1$tm-%1$td %1$tH:%1$tM\n", date);
    }
  }

  private void onInput() throws Exception {
    Member member = new Member();

    member.name = Prompt.inputString("이름? ");
    member.email = Prompt.inputString("이메일? ");
    member.password = Prompt.inputString("암호? ");
    member.createdDate = System.currentTimeMillis();

    if (memberDao.insert(member)) {
      System.out.println("회원을 등록했습니다.");
    } else {
      System.out.println("회원 등록에 실패했습니다.");
    }
  }

  private void onDelete() throws Exception {
    String email = Prompt.inputString("삭제할 회원 이메일? ");

    while (true) {
      try {
        email = Prompt.inputString("삭제할 게시글 번호? ");
        break;
      } catch (Exception ex) {
        System.out.println("입력 값이 옳지 않습니다!");
      }
    }

    if (memberDao.delete(email)) {
      System.out.println("삭제하였습니다.");
    } else {
      System.out.println("해당 번호의 게시글이 없습니다!");
    }
  }

  private void onUpdate() throws Exception {
    String email = Prompt.inputString("변경할 회원 이메일? ");

    while (true) {
      try {
        email = Prompt.inputString("변경할 게시글 번호? ");
        break;
      } catch (Throwable ex) {
        System.out.println("입력 값이 옳지 않습니다!");
      }
    }

    Member member = memberDao.findByEmail(email);

    if (member == null) {
      System.out.println("해당 이메일의 회원이 없습니다!");
      return;
    }

    member.name = Prompt.inputString("이름?(" + member.name + ") ");

    String input = Prompt.inputString("변경하시겠습니까?(y/n) ");
    if (input.equals("y")) {

      if (memberDao.update(member)) {
        System.out.println("변경했습니다.");
      } else {
        System.out.println("변경에 실패했습니다.");
      } 

    } else {
      System.out.println("변경을 취소했습니다.");
    }
  }
}



