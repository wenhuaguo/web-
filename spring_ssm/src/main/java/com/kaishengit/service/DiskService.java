package com.kaishengit.service;

import com.kaishengit.pojo.Disk;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by Acer on 2017/2/21.
 */
public interface DiskService {
    List<Disk> findDiskByFid(Integer path);

    void newFolder(Disk disk);

    void fileUpload(MultipartFile file, Integer fid);

    InputStream downLoad(Integer file) throws FileNotFoundException;

    Disk findDiskById(Integer fileId);

    void delFileOrFolder(Integer id);
}
