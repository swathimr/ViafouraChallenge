package com.viafoura.controllers;

import com.viafoura.service.AnagramService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.viafoura.utils.StringUtil.ANAGRAM_LIST;
import static com.viafoura.utils.StringUtil.ARE_ANAGRAM;

@RestController
public class AnagramController {

  AnagramService anagramService;

  @Autowired
  public AnagramController(AnagramService anagramService) {
    this.anagramService = anagramService;
  }

  @RequestMapping(value = "/anagrams/{string1}/{string2}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> validateAnagram(
          @PathVariable("string1") String string1, @PathVariable("string2") String string2) {

    if (string1 == null || string2 == null) {
      throw new RuntimeException("Please include a valid String");
    }
    boolean result = anagramService.isValidAnagram(string1, string2);
    JSONObject json = new JSONObject();
    json.put(ARE_ANAGRAM, result);
    return ResponseEntity.ok(json.toString());
  }

  @RequestMapping(value = "/anagrams/{string1}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> getAllAnagrams(@PathVariable("string1") String str) {
    if (str == null) {
      throw new RuntimeException("Please include a valid String");
    }
    List<String> anagramList = this.anagramService.getAllAnagramsInAString(str);
    JSONObject json = new JSONObject();
    json.put(ANAGRAM_LIST, anagramList);
    return ResponseEntity.ok(json.toString());
  }
}
