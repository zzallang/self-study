package com.self.lang.example02;

// 흐름 제어문 - 중첩된 반복문 탈출

public class exam06DoWhile {

  public static void main (String[] args) {
    int i = 0;

    // 1~10까지 출력
    do
      System.out.println(++i);
    while (i < 10);

    System.out.println("---------------------");

    // 여러 개의 문장을 반복할 때는 블록으로 묶기
    i = 0;
    do {
      i += 1;
      System.out.println(i);
    } while (i < 10);
  }
}

/* do ~ while
    최소 한 번은 반복한다.
    한 번 이상 반복하면 do ~ while
    0 번 이상 반복하면 while

    do
      문장1;
    while (조건);

    do {
      문장1;
      문장2;
      문장3;
    } while (조건);
 */
