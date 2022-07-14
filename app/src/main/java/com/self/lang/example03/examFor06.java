package com.self.lang.example03;

// 흐름 제어문 - for 중첩과 break

public class examFor06 {

  public static void main (String[] args) {
    // for 문의 중첩
    for (int i = 1; i <= 10; i++) {
      for (int j = 1; j <= i; j++) {
        System.out.print(j + " ");
      }
      System.out.println();
    }
  }
}
