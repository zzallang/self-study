package com.self.lang.example02;

public class exam03break {

  public static void main (String[] args) {
    int count = 0;
    int sum = 0;
    
    // 1~100까지 카운트 하는데 단, 합은 50까지만
    // -> break 사용 전
    count = 0;
    sum = 0;
    while (count < 100) {
      count++;
      if (count > 50) // 50을 넘어가면 합을 수행하지 않지만 100까지 반복
        continue;
      sum += count;
    }
    System.out.printf("count=%d, sum=%d\n", count, sum);

    System.out.printf("-----------------------------");

    // -> break 사용 후
    count = 0;
    sum = 0;
    while (count < 100) {
      count++;
      if (count > 50) //여기부터 count는 0 아니고 1임 ++이 뒤에 붙어서
        break; // 즉시 반복문을 종료하고 나감.
      sum += count; 
    }
    System.out.printf("count=%d, sum=%d\n", count, sum);

  }
}
