package com.jakobniinja;

public class MyStringMethods {

  public static String replaceStringAt(String originalString, int index, String replaceString) {
    String newString = originalString;
    if (index < originalString.length()) {
      String firstString = originalString.substring(0, index);
      String secondString = originalString.substring(index + replaceString.length());
      newString = firstString + replaceString + secondString;
    }

    return newString;
  }
}
