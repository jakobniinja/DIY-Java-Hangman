package com.jakobniinja;

import com.godtsoft.diyjava.DIYWindow;

public class MyWindow extends DIYWindow {

  public String promptforString(String prompt) {
    print(prompt);
    return input();
  }

  public boolean promptForYesNo(String message) {
    print(message);
    return input().contains("y");
  }
}
