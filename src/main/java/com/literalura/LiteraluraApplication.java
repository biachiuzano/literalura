package com.literalura;

import com.literalura.view.MenuConsola;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

    @Autowired
    private MenuConsola menu;

    public static void main(String[] args) {
        SpringApplication.run(LiteraluraApplication.class, args);
    }

    @Override
    public void run(String... args) {
        menu.mostrarMenu();
    }
}

