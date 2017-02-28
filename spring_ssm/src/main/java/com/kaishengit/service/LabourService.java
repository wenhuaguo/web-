package com.kaishengit.service;

import com.kaishengit.dto.LabourRentDto;
import com.kaishengit.pojo.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

/**
 * Created by Acer on 2017/2/18.
 */
public interface LabourService {
    List<LabourType> findAll();

    LabourType findLabourById(Integer id);

    String addLabourRentContract(LabourRentDto labourRent);

    LabourContract findCompanyLabourContract(String id);

    List<LabourDetail> findLabourDetailByRentId(Integer id);

    List<DeviceDoc> findFileUploadListByRentId(Integer id);

    List<LabourContract> findLabourContractByQueryParam(Map<String, String> queryParam);

    Long count();

    void changeContractStateById(Integer id);

    LabourContract findLabourContractById(Integer id);

    void downloadZip(LabourContract labourContract, ZipOutputStream zipOutputStream) throws IOException;

    InputStream downloadFile(Integer id) throws IOException;

    DeviceDoc findDeviceDoById(Integer id);
}
