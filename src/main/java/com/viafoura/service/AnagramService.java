package com.viafoura.service;

import com.viafoura.exception.BadRequestException;
import com.viafoura.utils.StringUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.viafoura.utils.StringUtil.INVALID_STRING;

@Service
public class AnagramService {

  /**
   * Takes str1 and string 2 as input and checks against each other
   *
   * @param str1
   * @param str2
   * @return
   */
  public boolean isValidAnagram(String str1, String str2) {

    if (!isValidString(str1) || !isValidString(str2)) {
      throw new BadRequestException(INVALID_STRING);
    }

    if (str1.length() != str2.length()) return false;

    str1 = str1.toLowerCase();
    str2 = str2.toLowerCase();

    int[] arr = new int[26];

    for (int i = 0; i < str1.length(); i++) {
      arr[str1.charAt(i) - 'a']++;
      arr[str2.charAt(i) - 'a']--;
    }

    for (int i : arr) {
      if (i != 0) {
        return false;
      }
    }
    return true;
  }

  /**
   * Rearranging the characters to find different combinations
   *
   * @param str
   * @return list of possible anagrams
   */
  public List<String> getAllAnagramsInAString(String str) {

    if (!isValidString(str)) {
      throw new BadRequestException(INVALID_STRING);
    }

    try {
      List<String> result = new ArrayList<>();
      permute(str, 0, result);
      return result;
    } catch (Exception ex) {
      throw new RuntimeException("Error while generating Anagrams of a String");
    }
  }

  /**
   * Backtracking approach used to rearrange the characters Assumption: Unable to validate the
   * rearranged word to see if its a valid word with meaning or not
   *
   * @param str
   * @param left
   * @param result
   */
  public void permute(String str, int left, List<String> result) {
    if (left == str.length() - 1) {
      result.add(str);
//      return;
    } else {
      for (int i = left; i <= str.length() - 1; i++) {
        str = swap(left, i, str);
        permute(str, left + 1, result);
        str = swap(left, i, str);
      }
    }
  }

  /**
   * Swap characters at position
   *
   * @param left
   * @param right
   * @param str
   * @return
   */
  private String swap(int left, int right, String str) {
    char[] input = str.toCharArray();
    char temp = input[left];
    input[left] = input[right];
    input[right] = temp;
    return String.valueOf(input);
  }

  public boolean isValidString(String str) {
    return StringUtil.isAlphaNumeric(str);
  }
}
