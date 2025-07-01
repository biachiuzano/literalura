package com.literalura.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nombre;

    private Integer nacimiento;
    private Integer fallecimiento;

    // Relación 1-N con Libro
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Libro> libros = new ArrayList<>();

    /* ─────────── CONSTRUCTORES ─────────── */

    public Autor() {}                              // ¡obligatorio para JPA!

    public Autor(String nombre) {                  // ←  NUEVO
        this.nombre = nombre;
    }

    /* ─────────── GETTERS / SETTERS ─────────── */

    public Long getId() { return id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Integer getNacimiento() { return nacimiento; }
    public void setNacimiento(Integer nacimiento) { this.nacimiento = nacimiento; }

    public Integer getFallecimiento() { return fallecimiento; }
    public void setFallecimiento(Integer fallecimiento) { this.fallecimiento = fallecimiento; }

    public List<Libro> getLibros() { return libros; }          // ←  NUEVO
    public void setLibros(List<Libro> libros) { this.libros = libros; }

    @Override
    public String toString() {
        return nombre + " (" + nacimiento + " - " +
               (fallecimiento != null ? fallecimiento : "…") + ")";
    }
}
