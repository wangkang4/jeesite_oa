package com.thinkgem.jeesite.modules.CostExcel.utils;

import com.thinkgem.jeesite.modules.CostExcel.entity.CostRecondExcel;
import org.apache.poi.hssf.usermodel.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class ExportExcel {

    /**
     * excel导出
     *
     * @param excelName 文件名
     * @param list
     * @param response
     * @throws IOException
     */
    public static void exportExcel(List<CostRecondExcel> list, HttpServletResponse response) throws IOException {
        // 第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook ();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet

        HSSFSheet sheet = wb.createSheet ( "销售" );

        /*-----设置自动换行-----*/
        sheet.autoSizeColumn ( 1, true );
        /*-----字体1-----*/
        HSSFFont font = wb.createFont ();
        font.setFontName ( "仿宋_GB2312" );
        font.setBoldweight ( HSSFFont.BOLDWEIGHT_BOLD );// 粗体显示
        font.setFontHeightInPoints ( (short) 14 );

        HSSFCellStyle style = wb.createCellStyle ();
        style.setFont ( font );
        style.setAlignment ( HSSFCellStyle.ALIGN_CENTER ); // 创建一个居中格式
        /*-----创建第一行-----*/
        HSSFRow row1 = sheet.createRow ( 0 );
        HSSFCell c0 = row1.createCell ( 0 );
        c0.setCellValue ( "客户名称" );
        HSSFCell c1 = row1.createCell ( 1 );
        c1.setCellValue ( "项目名称" );
        HSSFCell c2 = row1.createCell ( 2 );
        c2.setCellValue ( "项目奖金（万元）" );
        HSSFCell c3 = row1.createCell ( 3 );
        c3.setCellValue ( "拜访时间" );
        HSSFCell c4 = row1.createCell ( 4 );
        c4.setCellValue ( "差旅费（含油费）" );
        HSSFCell c5 = row1.createCell ( 5 );
        c5.setCellValue ( "餐费" );
        HSSFCell c6 = row1.createCell ( 6 );
        c6.setCellValue ( "文化礼品（自购）" );
        HSSFCell c7 = row1.createCell ( 7 );
        c7.setCellValue ( "其他费用" );
        HSSFCell c8 = row1.createCell ( 8 );
        c8.setCellValue ( "文化礼品（公司）" );
        HSSFCell c9 = row1.createCell ( 9 );
        c9.setCellValue ( "说明" );


        int j = 2;// 从第2行开始写
        HSSFRow row = null;
        for (int i = 0; i < list.size (); i++) {
            if ("1".equals ( list.get ( i ).getType () )) {// 1:销售

                row = sheet.createRow ( j++ );
                row.createCell ( 0 ).setCellValue ( list.get ( i ).getClientName () );// 客户名称
                row.createCell ( 1 ).setCellValue ( list.get ( i ).getProjectName () );// 项目名称
                row.createCell ( 2 ).setCellValue ( list.get ( i ).getProjectMoney () );//项目奖金（万元）
                row.createCell ( 3 ).setCellValue ( list.get ( i ).getUseTime () );// 拜访时间
                row.createCell ( 4 ).setCellValue ( list.get ( i ).getTravelExpense () );// 差旅费（含油费）
                row.createCell ( 5 ).setCellValue ( list.get ( i ).getMealMoney () );//餐费
                row.createCell ( 6 ).setCellValue ( list.get ( i ).getCulturalgiftsPerson () );// 文化礼品（自购）
                row.createCell ( 7 ).setCellValue ( list.get ( i ).getOtherMoney () );// 其他费用
                row.createCell ( 8 ).setCellValue ( list.get ( i ).getCulturalgiftsCompeny () );// 文化礼品（公司）
                row.createCell ( 9 ).setCellValue ( list.get ( i ).getConment () );// 说明
//			}else if ("2".equals(list.get(i).getType())) {// 2:技术
//				HSSFSheet sheet = wb.createSheet("技术");
//				
//				/*-----设置自动换行-----*/
//				sheet.autoSizeColumn(1, true);
//				/*-----字体1-----*/
//				HSSFFont font = wb.createFont();
//				font.setFontName("仿宋_GB2312");
//				font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 粗体显示
//				font.setFontHeightInPoints((short) 14);
//
//				HSSFCellStyle style = wb.createCellStyle();
//				style.setFont(font);
//				style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
//				/*-----创建第一行-----*/
//				HSSFRow row1 = sheet.createRow(0);
//				HSSFCell c0 = row1.createCell(0);c0.setCellValue("客户名称");
//				HSSFCell c1 = row1.createCell(1);c1.setCellValue("项目名称");
//				HSSFCell c2 = row1.createCell(2);c2.setCellValue("项目奖金");
//				HSSFCell c3 = row1.createCell(3);c3.setCellValue("拜访时间");
//				HSSFCell c4 = row1.createCell(4);c4.setCellValue("差旅费（含油费）");
//				HSSFCell c5 = row1.createCell(5);c5.setCellValue("餐费");
//				HSSFCell c6 = row1.createCell(6);c6.setCellValue("文化礼品（自购）");
//				HSSFCell c7 = row1.createCell(7);c7.setCellValue("其他费用");
//				HSSFCell c8 = row1.createCell(8);c8.setCellValue("文化礼品（公司）");
//				HSSFCell c9 = row1.createCell(9);c9.setCellValue("说明");
//				
//				row = sheet.createRow(j++);
//				row.createCell(0).setCellValue(list.get(i).getClientName());// 客户名称
//				row.createCell(1).setCellValue(list.get(i).getProjectName());// 项目名称
//				row.createCell(2).setCellValue(list.get(i).getProjectMoney());//项目奖金
//				row.createCell(3).setCellValue(list.get(i).getUseTime());// 拜访时间
//				row.createCell(4).setCellValue(list.get(i).getTravelExpense());// 差旅费（含油费）
//				row.createCell(5).setCellValue(list.get(i).getMealMoney());//餐费
//				row.createCell(6).setCellValue(list.get(i).getCulturalgiftsPerson());// 文化礼品（自购）
//				row.createCell(7).setCellValue(list.get(i).getOtherMoney());// 其他费用
//				row.createCell(8).setCellValue(list.get(i).getCulturalgiftsCompeny());// 文化礼品（公司）
//				row.createCell(9).setCellValue(list.get(i).getConment());// 说明
//			}else if ("3".equals(list.get(i).getType())) {// 3:行政
//				HSSFSheet sheet = wb.createSheet("行政");
//				
//				/*-----设置自动换行-----*/
//				sheet.autoSizeColumn(1, true);
//				/*-----字体1-----*/
//				HSSFFont font = wb.createFont();
//				font.setFontName("仿宋_GB2312");
//				font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 粗体显示
//				font.setFontHeightInPoints((short) 14);
//
//				HSSFCellStyle style = wb.createCellStyle();
//				style.setFont(font);
//				style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
//				/*-----创建第一行-----*/
//				HSSFRow row1 = sheet.createRow(0);
//				HSSFCell c0 = row1.createCell(0);c0.setCellValue("客户名称");
//				HSSFCell c1 = row1.createCell(1);c1.setCellValue("项目名称");
//				HSSFCell c2 = row1.createCell(2);c2.setCellValue("项目奖金");
//				HSSFCell c3 = row1.createCell(3);c3.setCellValue("拜访时间");
//				HSSFCell c4 = row1.createCell(4);c4.setCellValue("差旅费（含油费）");
//				HSSFCell c5 = row1.createCell(5);c5.setCellValue("餐费");
//				HSSFCell c6 = row1.createCell(6);c6.setCellValue("文化礼品（自购）");
//				HSSFCell c7 = row1.createCell(7);c7.setCellValue("其他费用");
//				HSSFCell c8 = row1.createCell(8);c8.setCellValue("文化礼品（公司）");
//				HSSFCell c9 = row1.createCell(9);c9.setCellValue("说明");
//				
//				row = sheet.createRow(j++);
//				row.createCell(0).setCellValue(list.get(i).getClientName());// 客户名称
//				row.createCell(1).setCellValue(list.get(i).getProjectName());// 项目名称
//				row.createCell(2).setCellValue(list.get(i).getProjectMoney());//项目奖金
//				row.createCell(3).setCellValue(list.get(i).getUseTime());// 拜访时间
//				row.createCell(4).setCellValue(list.get(i).getTravelExpense());// 差旅费（含油费）
//				row.createCell(5).setCellValue(list.get(i).getMealMoney());//餐费
//				row.createCell(6).setCellValue(list.get(i).getCulturalgiftsPerson());// 文化礼品（自购）
//				row.createCell(7).setCellValue(list.get(i).getOtherMoney());// 其他费用
//				row.createCell(8).setCellValue(list.get(i).getCulturalgiftsCompeny());// 文化礼品（公司）
//				row.createCell(9).setCellValue(list.get(i).getConment());// 说明
            }
        }


        String fileName = "合肥办费用统计.xlsx";

        response.setContentType ( "application/octet-stream;charset=utf-8" );
        response.setHeader ( "Content-Disposition",
                "attachment;filename=" + new String ( fileName.getBytes (), "iso-8859-1" ) );

        OutputStream ouputStream = response.getOutputStream ();
        wb.write ( ouputStream );
        ouputStream.flush ();
        ouputStream.close ();
    }

}
