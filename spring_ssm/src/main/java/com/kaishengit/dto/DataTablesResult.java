package com.kaishengit.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by Acer on 2017/2/20.
 */
@Data
@AllArgsConstructor
public class DataTablesResult {
    private String draw;
    private Object data;
    private Long recordsTotal;//记录总数
    private Long recordsFiltered;
}
