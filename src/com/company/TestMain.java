package com.company;

import java.util.ArrayList;

public class TestMain {

  public static void main(String[] args) {
    System.out.println(reverse(123));
    System.out.println(reverse(-123));
  }

  public static int reverse(int x) {
    if (x == 0) {
      return 0;
    }
    ArrayList<Integer> in = new ArrayList<>();
    boolean isNegative = x < 0;
    for (int i = 1; x != 0; i++) {
      int prep = x / 10;
      int ost = x - prep * 10;
      in.add(ost);
      x = prep;
    }
    int ans = 0;
    for (int i = 0; i < in.size(); i++) {
      ans += in.get(i) * ((int) Math.pow(10, in.size() - i - 1));
    }
    if (isNegative && ans > 0) {
      ans = ans * -1;
    }
    return ans;
  }

}
