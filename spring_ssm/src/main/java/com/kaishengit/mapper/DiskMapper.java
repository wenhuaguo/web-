package com.kaishengit.mapper;

import com.kaishengit.pojo.Disk;

import java.util.List;

/**
 * Created by Acer on 2017/2/21.
 */
public interface DiskMapper {
    List<Disk> findDiskByFid(Integer path);

    void save(Disk disk);

    Disk findDiskById(Integer fileId);

    List<Disk> findAll();

    void batchDel(List<Integer> delIdList);

    void del(Integer id);
}
