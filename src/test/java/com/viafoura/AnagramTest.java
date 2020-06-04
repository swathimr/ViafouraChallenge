package com.viafoura;

import com.viafoura.service.AnagramService;
import com.viafoura.utils.StringUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class AnagramTest {

    AnagramService anagramService;
    String str1 = "eat";;
    String str2 = "tea";
    String invalidString = "test@";

    @Before
    public void setUp() {
        anagramService = new AnagramService();
    }

    @Test
    public void testValidAnagram() {
        boolean isAnagram = anagramService.isValidAnagram(str1, str2);
        Assert.assertTrue(isAnagram);
    }

    @Test
    public void testValidString() {
        boolean isValidString =  StringUtil.isAlphaNumeric(str1);
        Assert.assertTrue(isValidString);
        isValidString = StringUtil.isAlphaNumeric(invalidString);
        Assert.assertFalse(isValidString);
    }

    @Test
    public void getAllAnagrams() {
        List<String> anagramList = anagramService.getAllAnagramsInAString(str1);
        Assert.assertNotNull(anagramList);
        Assert.assertEquals(6, anagramList.size());
    }
}
