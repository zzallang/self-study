package com.self.lang.example01;

// 흐름 제어문 - 반복문 while

public class switchExam05 {

  public static void main (String[] args) {
    int count = 0;

    // 문법 1:
    //        while (조건) 문장;
    // -> 조건이 참인 동안 문장을 계속 실행
    while (count < 5) System.out.println(conut++);

    System.out.println("---------------------------");

    // 문법 2:
    //        while (조건)
    //            문장;
    // -> 위와 동일
    count = 0;
    while (count < 5)
      System.out.println(count++);

    System.out.println("---------------------------");

    // 문법 3:
    //       while (조건) {}
    // -> 여러 개의 문장을 반복 실행하려면 블록으로 묶기
    count = 0;
    while (count < 5) {
      System.out.println(count);
      count++;
    }
  }
}
