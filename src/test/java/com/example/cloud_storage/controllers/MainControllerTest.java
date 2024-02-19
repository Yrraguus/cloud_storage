package com.example.cloud_storage.controllers;

import com.example.cloud_storage.entities.File;
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
import org.springframework.http.HttpStatus;
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
    private UserService userService;
    @Mock
    private FileService fileService;
    @InjectMocks
    public MainController mainController;
    Principal principal = Mockito.mock(Principal.class);
    String userName = "user1";

    @Test
    public void testGetFile() {
        File file = new File();
    }

    @Test
    public void testGetAllFiles() {

        // нужна заглушка principal
//        Principal principal = Mockito.mock(Principal.class);
//        Principal principal = new UsernamePasswordAuthenticationToken("user1", "100");
//        String authToken = "";
//        User user = new User();
//        user.setId(1);
//        when(userService.findByUsername(principal.getName())).thenReturn(Optional.of(user));
//
//        List<File> fileList = List.of(
//                new File(1L, "file1", new byte[128], 128L, user),
//                new File(2L, "file2", new byte[512], 512L, user)
//        );
        // список информации о файлах, который должен возвращать fileService
        List<FileInfo> fileInfoList = List.of(
                new FileInfo("file1", 1000000),
                new FileInfo("file2", 2000000),
                new FileInfo("file3", 3000000)
        );
//указываем что должен возвращать fileService
        when(fileService.getListOfFiles(3, principal.getName())).thenReturn(fileInfoList);
//result вызываем сам тестируемый метод
        ResponseEntity<List<FileInfo>> result = mainController.getAllFiles(3, principal);
        ResponseEntity<List<FileInfo>> expected = new ResponseEntity<>(fileInfoList, HttpStatus.OK);

        //проверяем возвращаемые параметры:
        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(expected, result);
//        assertEquals(fileInfoList, responseEntity.getBody());

        //returns Actual   :<200 OK OK,[FileInfo(filename=file1, size=1000000), FileInfo(filename=file2, size=2000000)],[]>
    }
}
