package com.self.lang.example02;

public class exam02continue {

  public static void main (String[] args) {
    int count = 0;
    int sum = 0;

    
    // 1~100 짝수의 합은?
    // -> continue 사용 전
    count = 0;
    sum  = 0;
    while (count < 100) {
      count++;
      if ((count & 1) == 0) {// count & 1 ==> count & 0x01 ==> count % 2
        sum += count;
      }
    }
    System.out.printf("count=%d, sum=%d\n", count, sum);

    System.out.println("------------------------------");


    // continue 사용 후
    count = 0;
    sum = 0;
    while (count < 100) {
      count++;
      if (count % 2 == 1)
        continue; // 다음 문장을 실행하지 않고 즉시 조건 검사로 이동.
      sum += count;
    }
    System.out.printf("count=%d, sum=%d\n", count, sum);
  }
}
