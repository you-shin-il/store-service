package com.store.file.repository;

import com.store.file.entity.FileMetadata;
import com.store.file.entity.FileType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileMetadataRepository extends JpaRepository<FileMetadata, Long> {

    List<FileMetadata> findByFileTypeAndReferenceId(FileType fileType, Long referenceId);
}
