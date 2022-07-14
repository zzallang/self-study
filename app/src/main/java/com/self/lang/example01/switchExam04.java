package com.self.lang.example01;
// enum 사용
public class switchExam04 {

  
  enum Level {
    GUEST, MEMBER, ADMIN
  }
    public static void main (String[] args) {
      // enum으로 정의된 상수를 사용하려면 enum타입의 변수를 선언해야 함.
      // final int 처럼 직접 값을 지정하지 않아도 됨.
      
      // 사용하는 이유
      // -> 100,200,"admin"과 같이 값을 직접 지정할 필요 X
      // -> enum 변수에는 그 타입에 정의된 값만 저장할 수 있음.
      // -> 안전한 코드 작성 O
      
      Level level = Level.MEMBER;

      switch (level) {
        case GUEST:
          System.out.println("");
          break;
        case MEMBER:
          System.out.println("");
          break;
        case ADMIN:
          System.out.println("");
          break;
    }
  }
}
