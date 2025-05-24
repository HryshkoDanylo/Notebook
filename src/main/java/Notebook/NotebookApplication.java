package Notebook;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.awt.*;
import java.net.URI;

@SpringBootApplication
public class NotebookApplication {
    @PostConstruct
public void logEnv() {
    System.out.println("DB URL: " + System.getenv("PGHOST"));
    System.out.println("DB USER: " + System.getenv("PGUSER"));
    System.out.println("DB PASS: " + System.getenv("PGPASSWORD"));
}

    public static void main(String[] args) {
        SpringApplication.run(NotebookApplication.class, args);

        // Відкриваємо браузер
        try {
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(new URI("http://localhost:8080/notes"));
            }
        } catch (Exception e) {
            System.err.println("Не вдалося відкрити браузер: " + e.getMessage());
        }

    }
}
