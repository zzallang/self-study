package com.self.lang.example03;

public class examFor01 {

  public static void main (String[] args) {
    // for (변수 선언 및 초기화; 조건; 증감문) 문장;
    // for (변수 선언 및 초기화; 조건; 증감문) {문장1; 문장2; …}

    // for 문의 전형적인 예
    for (int i = 1; 1 <= 5; i++)
      System.out.println(i);
  }
}

  // for 문에서 선언한 변수는 그 for 문 안에서만 사용할 수 있다.
  //    System.out.println(i); // 컴파일 오류!
