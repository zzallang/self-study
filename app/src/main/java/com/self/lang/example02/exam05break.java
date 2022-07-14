package com.self.lang.example02;

// 흐름 제어문 - 중첩된 반복문 탈출

public class exam05break {

  public static void main (String[] args) {
    int x = 2, y = 1;

    // 라벨 명 : 반복문1 { 반복문2 {break 라벨 명;}}
    // 라벨 문법 :
    //      라벨 : 문장;
    //      라벨 : {문장1, 문장2, …}

    myloop:
      while (x <= 9) {

        while (y <= 9) {
          System.out.printf("%d * %d = %d\n", x, y, x * y);
          if (x == 5 && y == 5)
            break myloop; // myloop에 소속된 문장을 나감.
          y++;
        }
      
        System.out.println();
        x++;
        y = 1;
      }
      System.out.println("End");
      System.out.println("-------------------")
  }
}
