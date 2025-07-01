package com.literalura.service;

import java.io.IOException;
import java.util.DoubleSummaryStatistics;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.literalura.dto.BookDTO;
import com.literalura.dto.BookResultsDTO;
import com.literalura.http.BookClient;
import com.literalura.model.Autor;
import com.literalura.model.Libro;
import com.literalura.repository.AutorRepository;
import com.literalura.repository.LibroRepository;

@Service
public class LibroService {

    private final LibroRepository libroRepo;
    private final AutorRepository autorRepo;
    private final BookClient client;
    private final ObjectMapper mapper = new ObjectMapper();

    public LibroService(LibroRepository libroRepo, AutorRepository autorRepo, BookClient client) {
        this.libroRepo = libroRepo;
        this.autorRepo = autorRepo;
        this.client = client;
    }

    public Libro buscarYGuardar(String titulo) {
    String json = client.buscarLibroPorTitulo(titulo);
    try {
        BookResultsDTO resultados = mapper.readValue(json, BookResultsDTO.class);
        if (resultados.getResults().isEmpty()) return null;

        BookDTO dto = resultados.getResults().get(0);

        // Verificar si el libro ya existe por ID
        if (libroRepo.existsById(dto.getId())) {
            System.out.println("⚠️ El libro ya está registrado en la base de datos.");
            return libroRepo.findById(dto.getId()).orElse(null);
        }

        // Obtener autor (o autor desconocido si no hay datos)
        BookDTO.AuthorDTO autorDto = dto.getAuthors().isEmpty() ? null : dto.getAuthors().get(0);
        Autor autor;

        if (autorDto != null) {
            // Buscar autor existente por nombre
            autor = autorRepo.findByNombre(autorDto.getName())
                    .orElseGet(() -> {
                        Autor nuevoAutor = new Autor();
                        nuevoAutor.setNombre(autorDto.getName());
                        nuevoAutor.setNacimiento(autorDto.getBirthYear());
                        nuevoAutor.setFallecimiento(autorDto.getDeathYear());
                        return autorRepo.save(nuevoAutor);
                    });
        } else {
            // Autor desconocido
            autor = autorRepo.findByNombre("Autor desconocido")
                    .orElseGet(() -> autorRepo.save(new Autor("Autor desconocido")));
        }

        // Crear y guardar el libro
        Libro libro = new Libro();
        libro.setId(dto.getId());
        libro.setTitulo(dto.getTitle());
        libro.setIdioma(dto.getLanguages().isEmpty() ? "desconocido" : dto.getLanguages().get(0));
        libro.setDescargas(dto.getDownloadCount());
        libro.setAutor(autor);

        return libroRepo.save(libro);

    } catch (IOException e) {
        throw new RuntimeException("Error al parsear JSON: " + e.getMessage());
    }
}
    public DoubleSummaryStatistics estadisticasDescargas() {
    return libroRepo.findAll()
                    .stream()
                    .mapToDouble(Libro::getDescargas)
                    .summaryStatistics();
    }
    
    public List<Libro> listarTodos() {
        return libroRepo.findAll();
    }

    public List<Libro> buscarPorIdioma(String idioma) {
        return libroRepo.findByIdioma(idioma);
    }

    public long contarPorIdioma(String idioma) {
        return libroRepo.countByIdioma(idioma);
    }

    public List<Autor> listarAutores() {
        return autorRepo.findAll();
    }

    public List<Autor> autoresVivosEn(int año) {
        return autorRepo.findByNacimientoLessThanEqualAndFallecimientoGreaterThanEqualOrFallecimientoIsNull(año, año);
    }

    // 🔽 NUEVO MÉTODO CON SALIDA ESTILIZADA
    public void mostrarAutoresVivosEn(int año) {
        List<Autor> autores = autoresVivosEn(año);
        if (autores.isEmpty()) {
            System.out.println("No se encontraron autores vivos en el año " + año);
            return;
        }

        System.out.println("\nAutores vivos en el año " + año + ":");
        System.out.println("=".repeat(50));

        for (Autor autor : autores) {
            System.out.println("Autor: " + autor.getNombre());
            System.out.println("Fecha de nacimiento: " + autor.getNacimiento());
            System.out.println("Fecha de fallecimiento: " + (autor.getFallecimiento() != null ? autor.getFallecimiento() : "Desconocida"));
            System.out.println("Libros: " + autor.getLibros().stream()
                .map(Libro::getTitulo)
                .toList());
            System.out.println("-".repeat(50));
        }
    }
}


