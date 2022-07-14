package com.self.lang.example03;

public class examFor02 {

  public static void main (String[] args) {
    // for (변수 선언 및 초기화; 조건; 증감문) 문장;
    // for (변수 선언 및 초기화; 조건; 증감문) {문장1; 문장2; …}

    // 변수 선언 및 초기화 문장 제거
    int i = 1;
    for (   ; 1 <= 5;     ){
      System.out.println(i);
      i++;
    }

    // for 문을 종료한 후에도 i 변수를 사용할 수 있음.
    System.out.println(i);
  
  }
}
