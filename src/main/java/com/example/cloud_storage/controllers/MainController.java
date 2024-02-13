package com.example.cloud_storage.controllers;

import com.example.cloud_storage.entities.File;
import com.example.cloud_storage.model.FileInfo;
import com.example.cloud_storage.service.FileService;
import com.sun.istack.NotNull;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MainController {

    @Autowired
    private final FileService fileService;

    @PostMapping("/file")
    public ResponseEntity<?> uploadFile(@RequestHeader("auth-token") @NotNull String authToken,
                                        @RequestParam("filename") @NotNull String fileName,
                                        @RequestParam("file") MultipartFile fileContent,
                                        Principal principal) throws IOException {
        fileService.uploadFile(fileName, fileContent.getBytes(), fileContent.getSize(), principal);
        return ResponseEntity.ok().body("file uploaded");
    }

    @DeleteMapping("/file")
    public ResponseEntity<?> deleteFile(@RequestHeader("auth-token") @NotNull String authToken,
                                        @RequestParam("filename") @NotNull String fileName,
                                        Principal principal) {
        String response = fileService.deleteFile(fileName, principal);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/file")
    public ResponseEntity<byte[]> getFile(@RequestHeader("auth-token") @NotNull String authToken,
                                          @RequestParam("filename") @NotNull String fileName,
                                          Principal principal) {
        File file = fileService.getFile(principal, fileName);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + fileName)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(file.getData());
    }

    @PutMapping("/file")
    public ResponseEntity<String> renameFile(@RequestHeader("auth-token") @NotNull String authToken,
                                             @RequestParam("filename") @NotNull String fileName,
                                             @RequestBody String newFileName,
                                             Principal principal) throws ParseException {
        fileService.renameFile(principal, fileName, newFileName);
        return ResponseEntity.ok().body("file renamed");
    }

    @GetMapping("/list")
    public ResponseEntity<List<FileInfo>> getAllFiles(@RequestHeader("auth-token") @NotNull String authToken,
                                                      @RequestParam("limit") @NotNull Integer limit,
                                                      Principal principal) {
        List<FileInfo> fileInfo = fileService.getListOfFiles(authToken, limit, principal);
        return ResponseEntity.ok().body(fileInfo);
    }
}