package com.viafoura.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.util.List;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AnagramResponse {

    private Boolean areAnagrams;

    private List<String> anagrams;

    public AnagramResponse(Boolean areAnagrams) {
        this.areAnagrams = areAnagrams;
    }

    public AnagramResponse(List<String> anagrams) {
        this.anagrams = anagrams;
    }
}
