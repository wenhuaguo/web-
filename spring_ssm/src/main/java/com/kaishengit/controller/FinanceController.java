package com.kaishengit.controller;

import com.google.common.collect.Maps;
import com.kaishengit.dto.AjaxResult;
import com.kaishengit.dto.DataTablesResult;
import com.kaishengit.pojo.Finance;
import com.kaishengit.service.FinanceService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 * Created by Acer on 2017/2/23.
 */
@Controller
@RequestMapping("/finance/report")
public class FinanceController {

    @Autowired
    private FinanceService financeService;
    //财务报表日报
    @GetMapping("/day")
    public String dayReport(){
        return "setting/finance/dayReport";
    }

    /**
     * 日收入图表
     * @param date
     * @return
     */
    @GetMapping("/day/{type}/{date}/pie")
    @ResponseBody
    public AjaxResult chartContent(@PathVariable String type,@PathVariable String date){
        type = "in".equals(type) ?"收入":"支出";
        List<Map<String,Object>> pieData = financeService.findPieDtataByDate(date,type);
        System.out.println(pieData);
        System.out.println(pieData);
        return new AjaxResult(AjaxResult.SUCCESS,"",pieData);
    }
    //财务报表日报流水
    @GetMapping("/day/load")
    @ResponseBody
    public DataTablesResult dayReportLoad(HttpServletRequest request, Model model){


        String draw = request.getParameter("draw");
        String start = request.getParameter("start");
        String length = request.getParameter("length");
        String date = request.getParameter("date");
        //根据日期返回相应的数据对象

        Map<String,String> queryParam = Maps.newHashMap();
        queryParam.put("draw",draw);
        queryParam.put("start",start);
        queryParam.put("length",length);
        queryParam.put("date",date);
        List<Finance> financeList = financeService.findFinanceByQueryParam(queryParam);
        //查询总记录数
        Long count = financeService.count();
        //过滤条数
        Long filterCount = financeService.filterCount(queryParam);
        //查询过滤的总记录数如果没有限定条件他们两个其实是一样的
        //给客户端按照dataTables的要求进行返回数据
        return new DataTablesResult(draw,financeList,count,filterCount);
    }

    /**
     * 更改财务流的状态
     * @param id
     * @return
     */
    @GetMapping("/change/state")
    @ResponseBody
    public AjaxResult changeState(Integer id){
        financeService.changeFinanceStateById(id);
        return new AjaxResult(AjaxResult.SUCCESS);
    }

    /**
     * 导出一天的数据
     * 所有的导出实际上是文件的下载
     * @param date
     */
    @GetMapping("/day/{date}/data.xls")
    public void exportDateData(@PathVariable String date, HttpServletResponse response) throws IOException {
        //要明白工作表文本标签的区别
        //1.首先将所属这一天的数据查询出来
        List<Finance> financeList = financeService.findFinanceByCreateDate(date);
        //设置相应类型MImeType
        response.setContentType("application/vnd.ms-excel");
        //设置文件名称
        response.setHeader("Content-Disposition","attachment;filename=\"" + date +".xls\"");
        //1.Apache基金会对于导出微软相关文档创建工种表
        Workbook workbook = new HSSFWorkbook();
        //2.创建sheet标签表
        Sheet sheet = workbook.createSheet(date + "财务流水");
        //单元格样式可选
        //CellStyle cellStyle = workbook.createCellStyle();
        //3.创建行从0开始
        Row row = sheet.createRow(0);
        Cell c1 = row.createCell(0);//创建第一个单元格也是从0开始
        c1.setCellValue("财务流水号");
        row.createCell(1).setCellValue("创建日期");
        row.createCell(2).setCellValue("业务模块");
        row.createCell(3).setCellValue("业务流水号");
        row.createCell(4).setCellValue("金额");
        row.createCell(5).setCellValue("状态");
        row.createCell(6).setCellValue("备注");
        row.createCell(7).setCellValue("创建人");
        row.createCell(8).setCellValue("确认人");
        row.createCell(9).setCellValue("确认日期");

        //循环查询出来的集合
        for (int i=0;i < financeList.size();i++){
            Finance finance = financeList.get(i);
            Row dataRow = sheet.createRow(i+1);//因为数据行需要从第一行开始0行表示表头
            dataRow.createCell(0).setCellValue(finance.getSerialNum());
            dataRow.createCell(1).setCellValue(finance.getCreateDate());
            dataRow.createCell(2).setCellValue(finance.getModule());
            dataRow.createCell(3).setCellValue(finance.getModuleSerial());
            dataRow.createCell(4).setCellValue(finance.getMoney());
            dataRow.createCell(5).setCellValue(finance.getState());
            dataRow.createCell(6).setCellValue(finance.getRemark());
            dataRow.createCell(7).setCellValue(finance.getCreateUser());
            dataRow.createCell(8).setCellValue(finance.getConfirmUser());
            dataRow.createCell(9).setCellValue(finance.getConfirmDate());
        }
        //循环完之后进行其它设置自动调整列宽
        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);
        sheet.autoSizeColumn(2);
        sheet.autoSizeColumn(3);
        sheet.autoSizeColumn(6);
        //所有文件导出本质上都是文件下载
        OutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }
    //财务报表月报
    @GetMapping("/month")
    public String monthReport(){
        return "setting/finance/monthReport";
    }

    //财务报表年报
    @GetMapping("/year")
    public String yearReport(){
        return "setting/finance/yearReport";
    }
}
