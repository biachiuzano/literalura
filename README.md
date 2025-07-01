<p align="center">
  <img src="assets/banner.png" width="100%" alt="LiterAlura – Catálogo de libros">
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Status-En%20desarrollo-blueviolet" />
  <img src="https://img.shields.io/badge/Java-17-blue" />
  <img src="https://img.shields.io/badge/Spring_Boot-3.5.3-brightgreen" />
  <img src="https://img.shields.io/badge/PostgreSQL-16-blue?logo=postgresql&logoColor=white" />
  <img src="https://img.shields.io/badge/API-Gutendex-yellow" />
</p>

# 📘 Descripción del proyecto

**LiterAlura** es una aplicación de consola en Java + Spring Boot que consume la API pública **Gutendex** (catálogo de Project Gutenberg) y permite:

- Buscar libros por título y almacenarlos en una base **PostgreSQL**  
- Registrar/actualizar autores relacionados  
- Consultar listas, estadísticas e información histórica desde la BD  
- Explorar libros por idioma y autores vivos en un año dado

Todo se maneja mediante un **menú interactivo** en la terminal, con salidas limpias y amigables 🙌.

---

# 🛠️ Tecnologías utilizadas

| Herramienta | Versión | Uso |
|-------------|---------|-----|
| ☕ **Java** | 17 | Core del proyecto |
| 🌱 **Spring Boot** | 3.5.3 | Framework, inyección de dependencias, CLI |
| 🐘 **PostgreSQL** | 16 | Base de datos persistente |
| 📦 **Spring Data JPA / Hibernate** | 6.2 | ORM y queries |
| 🌐 **Gutendex API** | v1 | Datos de ~70 000 libros |
| 🛠️ **Maven Wrapper** | 3.9.10 | Build, dependencias |
| 💻 **VS Code** | (opcional) | Editor/IDE |

---

# ✨ Funcionalidades

- Menú de consola con emojis y validación de entrada  
- **Buscar libro** → guarda libro + autor, evita duplicados  
- **Listar** libros / autores / libros por idioma  
- **Autores vivos** en un año determinado  
- **Estadísticas por idioma** (cantidad de libros)  
- **Top 10** libros por descargas (opcional)  
- **Estadísticas globales** usando `DoubleSummaryStatistics`  
- Códigos de idioma traducidos a español (en, es, fr → Inglés, Español, Francés)

---

# ▶️ Cómo ejecutar

> Requisitos: Java 17 y PostgreSQL corriendo en `localhost:5432`.

1. **Clona el repo**  
   `git clone https://github.com/tu-usuario/literAlura.git
   cd literAlura`

2. **Crea la base (una vez)**
`sql
CREATE DATABASE literalura;`

3. Configura las credenciales
`Crea src/main/resources/application.properties con:
ini
spring.datasource.url=jdbc:postgresql://localhost:5432/literalura
spring.datasource.username=<tu_usuario>
spring.datasource.password=<tu_password>`
O bien exporta variables de entorno.

4. Compila y corre
`./mvnw clean install -DskipTests
./mvnw spring-boot:run`

5. Navega por el menú y ¡disfruta! 📚

📁 Estructura del proyecto
literAlura/
 ├─ .mvn/                   # Maven Wrapper
 
 ├─ src/
 
 │  ├─ main/
 
 │  │   ├─ java/com/literalura/
 
 │  │   │   ├─ dto/         # DTOs Gutendex
 
 │  │   │   ├─ http/        # Cliente HttpClient
 
 │  │   │   ├─ model/       # Entidades JPA (Libro, Autor)
 
 │  │   │   ├─ repository/  # Repositorios Spring Data
 
 │  │   │   ├─ service/     # Lógica de negocio
 
 │  │   │   └─ view/        # MenuConsola
 
 │  │   └─ resources/
 
 │  │       └─ application.properties (local)
 
 │  └─ test/                # Pruebas (si las agregas)
 
 ├─ assets/
 
 │   └─ banner.png
 
 ├─ pom.xml
 
 └─ README.md

 # 👩🏻‍💻 Desarrolladora

<p align="center">
  <a href="https://github.com/biachiuzano" target="_blank">
    <img src="https://github.com/user-attachments/assets/9f0e476a-1eb0-4884-aa5a-68e2768bb232" width="120" alt="Bianka Chiuzano" style="border-radius: 50%;" />
  </a>
  <br>
  <b>Bianka Chiuzano</b><br>
  <sub>Desarrolladora de software | Apasionada por la tecnología</sub>
</p>

# 📄 Licencia

Este proyecto fue desarrollado con fines educativos y es de libre uso.  
¡Podés compartirlo, modificarlo y seguir aprendiendo! ✨

