package com.literalura.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BookResultsDTO {
    private int count;
    private List<BookDTO> results;

    public int getCount() { return count; }
    public void setCount(int count) { this.count = count; }

    public List<BookDTO> getResults() { return results; }
    public void setResults(List<BookDTO> results) { this.results = results; }
}
