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

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FileService {
    private FileRepository fileRepository;
    private UserRepository userRepository;

    public void uploadFile(String filename, byte[] fileContent, Long size, Principal principal) {
        User user = userRepository.findByUsername(principal.getName()).orElseThrow(()
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

    public List<FileInfo> getListOfFiles(String authToken, int limit, Principal principal) {
        List<File> listFiles = fileRepository.findFilesByUser_Username(principal.getName());
        return listFiles.stream()
                .map(file -> FileInfo.builder()
                        .filename(file.getFileName())
                        .size(Math.toIntExact(file.getSize()))
                        .build()).collect(Collectors.toList());
    } //TODO use limit, remove String authToken

    public String deleteFile(String fileName, Principal principal) throws FileNotFoundException {
        fileRepository.delete(getFile(principal, fileName));
        return "file deleted";
    }

    public File getFile(Principal principal, String fileName) throws FileNotFoundException {
        return getFile(principal, fileName);
    }

    public void renameFile(Principal principal, String fileName, String newFileName) throws FileNotFoundException, ParseException {
        JSONObject jo = (JSONObject) new JSONParser().parse(newFileName);
        String newFileNameString = (String) jo.get("filename");
        User user = userRepository.findByUsername(principal.getName()).orElseThrow(()
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
