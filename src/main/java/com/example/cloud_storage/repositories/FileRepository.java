package com.example.cloud_storage.repositories;

import com.example.cloud_storage.entities.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Integer> {}
