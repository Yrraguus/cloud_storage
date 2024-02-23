package com.example.cloud_storage.service;

import com.example.cloud_storage.entities.File;
import com.example.cloud_storage.entities.User;
import com.example.cloud_storage.repositories.FileRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Testcontainers
@SpringBootTest
@AutoConfigureMockMvc
class FileServiceTest {
    @Autowired
    private MockMvc mvc;
    @Container
    private static final MySQLContainer<?> mysql = new MySQLContainer<>(DockerImageName.parse("mysql:latest"));
    @Autowired
    private FileRepository fileRepository;
    private final File expectedFile = File.builder()
            .id(1L)
            .fileName("fileName")
            .data(new byte[128])
            .size(128L)
            .user(new User())
            .build();

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mysql::getJdbcUrl);
        registry.add("spring.datasource.username", mysql::getUsername);
        registry.add("spring.datasource.password", mysql::getPassword);
        registry.add("spring.jpa.generate-ddl", () -> true);
    }

    @Test
    void uploadFile() {
        File file = File.builder()
                .id(1L)
                .fileName("fileName")
                .data(new byte[128])
                .size(128L)
                .user(new User())
                .build();
        fileRepository.save(file);
        assertEquals(file, expectedFile);
    }

    @Test
    void getListOfFiles() {
        File file = File.builder()
                .id(1L)
                .fileName("fileName")
                .data(new byte[128])
                .size(128L)
                .user(new User())
                .build();
        fileRepository.save(file);


    }

    @Test
    void deleteFile() {
    }

    @Test
    void getFile() {
    }

    @Test
    void renameFile() {
    }
}