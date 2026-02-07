package com.store.file.service;

import java.io.InputStream;

public interface FileStorageService {

    String upload(String path, String fileName, InputStream inputStream, String contentType);

    void delete(String path);

    String getPresignedUrl(String path);
}
