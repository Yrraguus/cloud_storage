//package com.example.cloud_storage.controllers;
//
//import com.example.cloud_storage.entities.File;
//import com.example.cloud_storage.entities.User;
//import com.example.cloud_storage.model.FileInfo;
//import com.example.cloud_storage.service.FileService;
//import com.example.cloud_storage.service.UserService;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.testcontainers.containers.MySQLContainer;
//import org.testcontainers.junit.jupiter.Testcontainers;
//
//import java.security.Principal;
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.when;
//
//@Testcontainers
//@SpringBootTest
//@RunWith(MockitoJUnitRunner.class)
//public class MainControllerTest {
//
//
//        @Mock
//        private UserService userService;
//        @Mock
//        private FileService fileService;
//        @InjectMocks
//        public MainController mainController;
//
//        @Test
//        public void testGetAllFiles() {
//            Principal mockPrincipal = Mockito.mock(Principal.class);
//            Principal principal = new UsernamePasswordAuthenticationToken("user1", "100");
//            String authToken = "";
//            User user = new User();
//            user.setId(1);
//            when(userService.findByUsername(principal.getName())).thenReturn(Optional.of(user));
//
//            List<File> fileList =  List.of(
//                    new File(1L, "file1", new byte[128], 128L, user),
//                    new File(2L, "file2", new byte[512], 512L, user)
//            );
//            FileInfo fileInfo1 = new FileInfo("file1", 100);
//            FileInfo fileInfo2 = new FileInfo("file2", 200);
//            List<FileInfo> fileInfoList = new ArrayList<FileInfo>();
//            fileInfoList.add(fileInfo1);
//            fileInfoList.add(fileInfo2);
//
//            when(fileService.getListOfFiles(authToken, 3, principal)).thenReturn(fileInfoList);
//
//            ResponseEntity<Object> responseEntity = ResponseEntity.ok(mainController.getAllFiles(authToken, 3, principal));
//            assertNotNull(responseEntity);
//            assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//            assertEquals(fileList, responseEntity.getBody());
//        }
//    }
