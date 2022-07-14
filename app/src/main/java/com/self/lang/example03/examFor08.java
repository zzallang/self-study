package com.self.lang.example03;

// 흐름 제어문 - for 중첩과 break
public class examFor08 {

  public static void main (String[] args) {
    // break 라벨명;
    loop1: {
      for (int i = 1; i < 10; i++) {
        for (int j = 1; j <= i; j++) {
          System.out.print(j + " ");
          break loop1;
        }
        System.out.println();
      }
      System.out.println("-----------------------");
    }
    System.out.println("loop 밖");
  }
}
