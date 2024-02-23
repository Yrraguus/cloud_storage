package com.example.cloud_storage.controllers;

import com.example.cloud_storage.entities.File;
import com.example.cloud_storage.entities.User;
import com.example.cloud_storage.model.FileInfo;
import com.example.cloud_storage.service.FileService;
import com.example.cloud_storage.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.security.Principal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@Testcontainers
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class MainControllerTest {

    @Mock
    private FileService fileService;
    @InjectMocks
    public MainController mainController;
    Principal principal = Mockito.mock(Principal.class);

    @Test
    public void testGetFile() {
        String fileName = "filename.txt";

        new File();
        when(fileService.getFile(principal.getName(), fileName)).thenReturn(File.builder()
                .id(1L)
                .fileName(fileName)
                .data(new byte[128])
                .size(128L)
                .user(new User())
                .build());

        ResponseEntity<byte[]> expected = ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + fileName)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(new byte[128]);
        ResponseEntity<byte[]> result = mainController.getFile(fileName, principal);

        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(expected, result);
    }

    @Test
    public void testGetAllFiles() {
        List<FileInfo> fileInfoList = List.of(
                new FileInfo("file1", 1000000),
                new FileInfo("file2", 2000000),
                new FileInfo("file3", 3000000)
        );

        when(fileService.getListOfFiles(3, principal.getName())).thenReturn(fileInfoList);
        ResponseEntity<List<FileInfo>> expected = new ResponseEntity<>(fileInfoList, HttpStatus.OK);
        ResponseEntity<List<FileInfo>> result = mainController.getAllFiles(3, principal);

        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(expected, result);
    }
}
