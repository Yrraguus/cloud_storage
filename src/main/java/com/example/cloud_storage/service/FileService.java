package com.example.cloud_storage.service;

import com.example.cloud_storage.entities.File;
import com.example.cloud_storage.entities.User;
import com.example.cloud_storage.exceptions.DuplicateFileNameException;
import com.example.cloud_storage.exceptions.FileNotFoundException;
import com.example.cloud_storage.model.FileInfo;
import com.example.cloud_storage.repositories.FileRepository;
import com.example.cloud_storage.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FileService {
    private FileRepository fileRepository;
    private UserRepository userRepository;

    public void uploadFile(String filename, byte[] fileContent, Long size, String userName) {
        User user = userRepository.findByUsername(userName).orElseThrow(()
                -> new UsernameNotFoundException(String.format("user not found")));
        if (fileRepository.findFileByUserIdAndFileName(user.getId(), filename).isPresent()) {
            throw new DuplicateFileNameException("duplicate file name");
        }
        File uploadFile = File.builder()
                .fileName(filename)
                .data(fileContent)
                .size(size)
                .user(user)
                .build();
        fileRepository.save(uploadFile);
    }

    public List<FileInfo> getListOfFiles(int limit, String userName) {
        List<File> listFiles = fileRepository.findFilesByUser_Username(userName);
        return listFiles.stream()
                .map(file -> FileInfo.builder()
                        .filename(file.getFileName())
                        .size(Math.toIntExact(file.getSize()))
                        .build())
                .limit(limit)
                .collect(Collectors.toList());
    }

    public String deleteFile(String fileName, String userName) throws FileNotFoundException {
        fileRepository.delete(getFile(userName, fileName));
        return "file deleted";
    }

    public File getFile(String userName, String fileName) throws FileNotFoundException {
        User user = userRepository.findByUsername(userName).orElseThrow(()
                -> new UsernameNotFoundException(String.format("user not found")));
        return fileRepository.findFileByUserIdAndFileName(user.getId(), fileName).orElseThrow(()
                -> new FileNotFoundException("file not found"));
    }

    public void renameFile(String userName, String fileName, String newFileName) throws FileNotFoundException, ParseException {
        JSONObject jo = (JSONObject) new JSONParser().parse(newFileName);
        String newFileNameString = (String) jo.get("filename");
        User user = userRepository.findByUsername(userName).orElseThrow(()
                -> new UsernameNotFoundException(String.format("user not found")));
        File file = fileRepository.findFileByUserIdAndFileName(user.getId(), fileName).orElseThrow(()
                -> new FileNotFoundException("file not found"));
        if (fileRepository.findFileByUserIdAndFileName(user.getId(), newFileNameString).isPresent()) {
            throw new DuplicateFileNameException("duplicate file name");
        }
        file.setFileName(newFileNameString);
        fileRepository.save(file);
    }
}
