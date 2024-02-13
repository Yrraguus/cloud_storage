package com.example.cloud_storage.repositories;

import com.example.cloud_storage.entities.File;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FileRepository extends JpaRepository<File, Integer> {
    Optional<File> findFileByUserIdAndFileName(Integer userId, String fileName);

    List<File> findFilesByUser_Username(String userName);
}
