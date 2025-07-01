package com.literalura.model;

import jakarta.persistence.*;

@Entity
public class Libro {

    @Id
    private Long id;

    private String titulo;
    private String idioma;
    private int descargas;

    @ManyToOne(cascade = CascadeType.ALL)
    private Autor autor;

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public int getDescargas() {
        return descargas;
    }

    public void setDescargas(int descargas) {
        this.descargas = descargas;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return "📘 " + titulo + " (" + idioma + ") - " + descargas + " descargas\n  → " + autor;
    }
}
