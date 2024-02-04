package com.example.cloud_storage.service;

import com.example.cloud_storage.entities.File;
import com.example.cloud_storage.entities.User;
import com.example.cloud_storage.repositories.FileRepository;
import com.example.cloud_storage.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Service
@AllArgsConstructor
public class FileService {
    private FileRepository fileRepository;
    private UserRepository userRepository;

    @PostMapping("/file")
    public boolean uploadFile(String filename, byte[] fileContent, Principal principal) {
        User user = userRepository.findByUsername(principal.getName()).orElseThrow(()
                -> new UsernameNotFoundException(String.format("user not found")));

        File uploadFile = File.builder()
                .fileName(filename)
                .data(fileContent)
                .user(user)
                .build();
        fileRepository.save(uploadFile);
        return true;
    }
}
