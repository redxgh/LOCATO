package com.locato.adservice.entities;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class CustomMultipartFile implements MultipartFile {

    private final MultipartFile multipartFile;
    private final String newName;

    public CustomMultipartFile(MultipartFile multipartFile, String newName) {
        Objects.requireNonNull(multipartFile, "MultipartFile must not be null");
        Objects.requireNonNull(newName, "New name must not be null");

        this.multipartFile = multipartFile;
        this.newName = newName;
    }

    @Override
    public String getName() {
        return newName;
    }

    @Override
    public String getOriginalFilename() {
        return multipartFile.getOriginalFilename();
    }

    @Override
    public String getContentType() {
        return multipartFile.getContentType();
    }

    @Override
    public boolean isEmpty() {
        return multipartFile.isEmpty();
    }

    @Override
    public long getSize() {
        return multipartFile.getSize();
    }

    @Override
    public byte[] getBytes() throws IOException {
        return multipartFile.getBytes();
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return multipartFile.getInputStream();
    }

    @Override
    public Resource getResource() {
        return MultipartFile.super.getResource();
    }

    @Override
    public void transferTo(File dest) throws IOException, IllegalStateException {

    }

    @Override
    public void transferTo(java.nio.file.Path dest) throws IOException, IllegalStateException {
        multipartFile.transferTo(dest);
    }
}