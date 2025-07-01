package com.literalura.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BookDTO {

    private Long id;
    private String title;

    @JsonAlias("download_count")
    private int downloadCount;

    private List<String> languages;
    private List<AuthorDTO> authors;

    // Getters y setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public int getDownloadCount() { return downloadCount; }
    public void setDownloadCount(int downloadCount) { this.downloadCount = downloadCount; }

    public List<String> getLanguages() { return languages; }
    public void setLanguages(List<String> languages) { this.languages = languages; }

    public List<AuthorDTO> getAuthors() { return authors; }
    public void setAuthors(List<AuthorDTO> authors) { this.authors = authors; }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class AuthorDTO {
        private String name;

        @JsonAlias("birth_year")
        private Integer birthYear;

        @JsonAlias("death_year")
        private Integer deathYear;

        // Getters y setters

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public Integer getBirthYear() { return birthYear; }
        public void setBirthYear(Integer birthYear) { this.birthYear = birthYear; }

        public Integer getDeathYear() { return deathYear; }
        public void setDeathYear(Integer deathYear) { this.deathYear = deathYear; }
    }
}
