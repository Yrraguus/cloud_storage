package com.example.cloud_storage.controllers;

import com.example.cloud_storage.entities.User;
import com.example.cloud_storage.repositories.FileRepository;
import com.example.cloud_storage.repositories.UserRepository;
import com.example.cloud_storage.service.AuthService;
import com.example.cloud_storage.service.FileService;
import com.sun.istack.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class MainController {

    @Autowired
    private final UserRepository userRepository;
    private final FileService fileService;

    @PostMapping("/file")
    public ResponseEntity<String> uploadFile(@RequestHeader("auth-token") @NotNull String authToken,
                                             @RequestParam("filename") @NotNull String fileName,
                                             @RequestBody @NotNull MultipartFile fileContent,
                                             Principal principal) throws IOException {
        fileService.uploadFile(fileName, fileContent.getBytes(), principal);
        return ResponseEntity.ok().body("file uploaded");
    }

    @GetMapping("/unsecured")
    public @ResponseBody Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/secured")
    public String securedData() {
        return "Secured data";
    }

    @GetMapping("/admin")
    public String adminData() {
        return "Admin data";
    }

    @GetMapping("/info")
    public String userData(Principal principal) {
        return principal.getName();
    }
}