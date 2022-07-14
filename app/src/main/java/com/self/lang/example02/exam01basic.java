package com.self.lang.example02;

public class exam01basic {

  public static void main (String[] args) {
    int count = 0;
    int sum = 0;

    // 1~100 까지의 합은?
    while (count < 100) {
      //count++;
      //sum += count; // sum = sum + count;
      sum+= ++count;
    }
    System.out.printf("count=%d, sum%d\n", count, sum);
  }
}
