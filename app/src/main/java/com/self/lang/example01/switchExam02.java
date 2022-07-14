package com.self.lang.example02;

public class swichExam02 {

  public static void main (String[] args) {
    int level = 1;

    swich (level) {
      case 0: 
        System.out.println("조회만 가능합니다");
        break;
      
      case 1:
        System.out.println("글작성만 가능합니다");
        break;

      case 2:
        System.out.println("다른 회원의 글을 변경 또는 삭제할 수 있습니다.");
        break;    
    }
  }
}
