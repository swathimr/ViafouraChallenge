package com.viafoura.controllers;

import com.viafoura.model.AnagramResponse;
import com.viafoura.service.AnagramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AnagramController {

  AnagramService anagramService;

  @Autowired
  public AnagramController(AnagramService anagramService) {
    this.anagramService = anagramService;
  }

  @RequestMapping(value = "/anagrams/{string1}/{string2}")
  public ResponseEntity<AnagramResponse> validateAnagram(
          @PathVariable("string1") String string1, @PathVariable("string2") String string2) {

    if (string1 == null || string2 == null) {
      throw new RuntimeException("Please include a valid String");
    }
    boolean result = anagramService.isValidAnagram(string1, string2);
    return ResponseEntity.ok(new AnagramResponse(result));
  }

  @RequestMapping(value = "/anagrams/{string1}")
  public ResponseEntity<AnagramResponse> getAllAnagrams(@PathVariable("string1") String str) {
    if (str == null) {
      throw new RuntimeException("Please include a valid String");
    }
    List<String> anagramList = this.anagramService.getAllAnagramsInAString(str);
    return ResponseEntity.ok(new AnagramResponse(anagramList));
  }
}
