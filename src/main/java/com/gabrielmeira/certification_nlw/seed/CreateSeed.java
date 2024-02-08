package com.gabrielmeira.certification_nlw.seed;
import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;

public class CreateSeed {
    private final DataSource dataSource;

    public CreateSeed(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static void main(String[] args) {
        DataSource dataSource = createDataSource();
        CreateSeed createSeed = new CreateSeed(dataSource);
        createSeed.run();
    }

    private static DataSource createDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/pg_nlw");
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres");
        return dataSource;
    }

    public void run(String... args) {
        executeFile("src/main/resources/create.sql");
    }

    private void executeFile(String filePath) {
        try {
            String sqlScript = new String(Files.readAllBytes(Paths.get(filePath)));
            try (Connection connection = dataSource.getConnection()) {
                connection.createStatement().execute(sqlScript);
                System.out.println("Seed operation completed successfully.");
            }
        } catch (SQLException | IOException e) {
            System.out.println("Error executing SQL script: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
