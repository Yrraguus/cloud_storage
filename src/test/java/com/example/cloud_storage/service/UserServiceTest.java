package com.example.cloud_storage.service;

import com.example.cloud_storage.CloudStorageApplication;
import com.example.cloud_storage.entities.User;
import com.example.cloud_storage.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = CloudStorageApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
class UserServiceTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    void findByUsername() {
        var user = userRepository.findByUsername("user1");
        assertTrue(user.isPresent());
        assertEquals("user1", user.get().getUsername());
    }
}