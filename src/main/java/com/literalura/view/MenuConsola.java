package com.literalura.view;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.literalura.model.Autor;
import com.literalura.model.Libro;
import com.literalura.service.LibroService;

@Component
public class MenuConsola {

    /* ──────────── DEPENDENCIAS Y UTILIDADES ──────────── */
    private final Scanner scanner = new Scanner(System.in);
    private final LibroService servicio;

    /** Traducción de códigos ISO-639-1 → nombre en español */
    private static final Map<String, String> IDIOMAS_ES = Map.of(
            "en", "Inglés",
            "es", "Español",
            "fr", "Francés",
            "pt", "Portugués",
            "de", "Alemán",
            "it", "Italiano"
    );

    private String nombreDeIdioma(String codigo) {
        return IDIOMAS_ES.getOrDefault(codigo, codigo);
    }

    public MenuConsola(LibroService servicio) {
        this.servicio = servicio;
    }

    /* ──────────── MENÚ PRINCIPAL ──────────── */
    public void mostrarMenu() {
        int opcion = -1;
        while (opcion != 8) {
            System.out.println("\n📚 === MENÚ LiterAlura ===");
            System.out.println("1 - Buscar libro por título");
            System.out.println("2 - Listar todos los libros registrados");
            System.out.println("3 - Listar libros por idioma");
            System.out.println("4 - Listar autores registrados");
            System.out.println("5 - Listar autores vivos en un determinado año");
            System.out.println("6 - Estadísticas por idioma");
            System.out.println("7 - Estadísticas globales de descargas");
            System.out.println("8 - Salir");
            System.out.print("Elija una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());
                switch (opcion) {
                    case 1 -> buscarLibro();
                    case 2 -> listarLibros();
                    case 3 -> librosPorIdioma();
                    case 4 -> listarAutores();
                    case 5 -> autoresVivos();
                    case 6 -> estadisticasPorIdioma();
                    case 7 -> mostrarEstadisticas();
                    case 8 -> System.out.println("👋 ¡Hasta la próxima!");
                    default -> System.out.println("⚠️ Opción inválida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Por favor ingrese un número válido.");
            }
        }
    }

    /* ──────────── 1. Buscar libro ──────────── */
    private void buscarLibro() {
        System.out.print("🔎 Ingresá el título del libro: ");
        String titulo = scanner.nextLine();
        Libro libro = servicio.buscarYGuardar(titulo);

        if (libro == null) {
            System.out.println("❌ No se encontraron resultados.");
            return;
        }
        System.out.println("\n----- LIBRO GUARDADO -----");
        System.out.println("Título: " + libro.getTitulo());
        System.out.println("Autor: " + libro.getAutor().getNombre());
        System.out.println("Idioma: " + nombreDeIdioma(libro.getIdioma()));
        System.out.println("Número de descargas: " + libro.getDescargas());
        System.out.println("---------------------------");
    }

    /* ──────────── 2. Listar todos los libros ──────────── */
    private void listarLibros() {
        List<Libro> libros = servicio.listarTodos();
        if (libros.isEmpty()) {
            System.out.println("📭 No hay libros guardados.");
            return;
        }
        for (Libro libro : libros) {
            System.out.println("\n----- LIBRO -----");
            System.out.println("Título: " + libro.getTitulo());
            System.out.println("Autor: " + libro.getAutor().getNombre());
            System.out.println("Idioma: " + nombreDeIdioma(libro.getIdioma()));
            System.out.println("Número de descargas: " + libro.getDescargas());
            System.out.println("-----------------");
        }
    }

    /* ──────────── 3. Listar libros por idioma ──────────── */
    private void librosPorIdioma() {
        System.out.print("🌐 Ingresá el código del idioma (ej: en, es, fr): ");
        String idioma = scanner.nextLine().trim();
        List<Libro> libros = servicio.buscarPorIdioma(idioma);

        if (libros.isEmpty()) {
            System.out.println("📭 No se encontraron libros en ese idioma.");
            return;
        }
        for (Libro libro : libros) {
            System.out.println("\n----- LIBRO -----");
            System.out.println("Título: " + libro.getTitulo());
            System.out.println("Autor: " + libro.getAutor().getNombre());
            System.out.println("Idioma: " + nombreDeIdioma(libro.getIdioma()));
            System.out.println("Número de descargas: " + libro.getDescargas());
            System.out.println("-----------------");
        }
    }

    /* ──────────── 4. Listar autores ──────────── */
    private void listarAutores() {
        List<Autor> autores = servicio.listarAutores();
        if (autores.isEmpty()) {
            System.out.println("📭 No hay autores registrados.");
            return;
        }
        for (Autor autor : autores) {
            System.out.println("\nAutor: " + autor.getNombre());
            System.out.println("Fecha de nacimiento: " + autor.getNacimiento());
            System.out.println("Fecha de fallecimiento: " +
                    (autor.getFallecimiento() != null ? autor.getFallecimiento() : "Desconocida"));
            System.out.println("Libros: " + autor.getLibros().stream()
                    .map(Libro::getTitulo)
                    .toList());
            System.out.println("-".repeat(50));
        }
    }

    /* ──────────── 5. Autores vivos en un año ──────────── */
    private void autoresVivos() {
        System.out.print("📅 Ingresá el año: ");
        try {
            int año = Integer.parseInt(scanner.nextLine());
            List<Autor> autores = servicio.autoresVivosEn(año);

            if (autores.isEmpty()) {
                System.out.println("❌ Ningún autor estaba vivo ese año.");
                return;
            }
            for (Autor autor : autores) {
                System.out.println("\nAutor: " + autor.getNombre());
                System.out.println("Fecha de nacimiento: " + autor.getNacimiento());
                System.out.println("Fecha de fallecimiento: " +
                        (autor.getFallecimiento() != null ? autor.getFallecimiento() : "Desconocida"));
                System.out.println("Libros: " + autor.getLibros().stream()
                        .map(Libro::getTitulo)
                        .toList());
                System.out.println("-".repeat(50));
            }
        } catch (NumberFormatException e) {
            System.out.println("⚠️ Año inválido.");
        }
    }

    /* ──────────── 6. Estadísticas por idioma ──────────── */
    private void estadisticasPorIdioma() {
        String disponibles = IDIOMAS_ES.entrySet().stream()
                .map(e -> e.getKey() + " (" + e.getValue() + ")")
                .collect(Collectors.joining(", "));
        System.out.println("📊 Idiomas disponibles: " + disponibles);

        System.out.print("Elegí un idioma: ");
        String idioma = scanner.nextLine().trim();

        long cantidad = servicio.contarPorIdioma(idioma);
        System.out.println("🔢 Cantidad de libros en '" +
                nombreDeIdioma(idioma) + "': " + cantidad);
    }

    /* ──────────── 7. Estadísticas globales de descargas ──────────── */
    private void mostrarEstadisticas() {
        var stats = servicio.estadisticasDescargas(); // Debe existir en LibroService
        if (stats.getCount() == 0) {
            System.out.println("📭 No hay datos aún.");
            return;
        }
        System.out.println("\n📈 Estadísticas de descargas");
        System.out.println("-".repeat(35));
        System.out.printf("Total de libros: %d%n", stats.getCount());
        System.out.printf("Suma de descargas: %.0f%n", stats.getSum());
        System.out.printf("Promedio de descargas: %.1f%n", stats.getAverage());
        System.out.printf("Máximo: %.0f  |  Mínimo: %.0f%n",
                stats.getMax(), stats.getMin());
        System.out.println("-".repeat(35));
    }
}
