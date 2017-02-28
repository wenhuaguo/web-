package com.kaishengit.service;

import java.io.InputStream;

/**
 * Created by Acer on 2017/2/17.
 */
public interface FileService {

    String upload(String originalFilename, String contentType, InputStream inputStream);
}
