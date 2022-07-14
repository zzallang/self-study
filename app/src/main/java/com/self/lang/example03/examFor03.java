package com.self.lang.example03;

public class examFor03 {

  public static void main (String[] args) {
    // for (변수 선언 및 초기화; 조건; 증감문) 문장;
    // for (변수 선언 및 초기화; 조건; 증감문) {문장1; 문장2; …}

    // 조건문 제거
    int i = 1;
    for (   ;    ;     ){
      if (i > 5)
        break; // 이럴 거면 while과 다를 것이 없음 / 무의미
      System.out.println(i);
      i++;
    }
  
  }
}
