package com.viafoura;

import com.viafoura.controllers.AnagramController;
import com.viafoura.service.AnagramService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doCallRealMethod;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AnagramController.class)
public class AnagramControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AnagramService anagramService;

    @Before
    public void setup() {
        Mockito.when(anagramService.isValidString(any())).thenCallRealMethod();
        Mockito.when(anagramService.isValidAnagram(any(),any())).thenCallRealMethod();
        Mockito.when(anagramService.getAllAnagramsInAString(any())).thenCallRealMethod();
        doCallRealMethod().when(anagramService).permute(any(), anyInt(), any());
    }
    @Test
    public void getAnagramValidity() throws Exception {
        mvc.perform(get("/anagrams/tea/eat")
        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("areAnagrams",is(true)));
    }

    @Test
    public void getAnagramForAString() throws Exception {
        mvc.perform(get("/anagrams/eat")
        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("anagrams",hasSize(6)))
                .andExpect(jsonPath("anagrams", containsInAnyOrder("eat","eta","aet","ate","tae","tea")));
    }

    @Test
    public void getAnagramValidityWithDifferentString() throws Exception {
        mvc.perform(get("/anagrams/tea/ert")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("areAnagrams",is(false)));
    }

    @Test
    public void getAnagramForInvalidStringTest() throws Exception {
        mvc.perform(get("/anagrams/eat@")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
