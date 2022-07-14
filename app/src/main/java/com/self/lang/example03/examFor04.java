package com.self.lang.example03;

public class examFor04 {

  public static void main (String[] args) {
    // for 문 안에 선언된 변수는 for 문을 나가는 순간 제거된다.
    for (int i = 1; i <= 5; i++)
      System.out.println(i);

    // 그래서 다음과 같이 i 변수의 값을 조회하려 하면 컴파일 오류 발생
    //    System.out.println(i);

    System.out.println("--------------");

    // 반복문을 종료한 뒤라도 해당 변수의 값을 사용하고 싶으면 반복문 밖에 변수 선언
    int x;
    for (x = 1; x <= 5; x++)
      System.out.println(x);
    System.out.printf("x = %d\n,", x);
  }
}
