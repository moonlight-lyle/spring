package com.it.handler;

import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.write.handler.AbstractCellWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteTableHolder;
import org.apache.poi.ss.usermodel.*;

import java.util.List;

public class CellWriteHandler extends AbstractCellWriteHandler {

    @Override
    public void afterCellDispose(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder,
                                 List<CellData> cellDataList, Cell cell, Head head, Integer relativeRowIndex, Boolean isHead) {
        int columnIndex = cell.getColumnIndex();
        int rowIndex = cell.getRowIndex();
        if (rowIndex > 1) {
            // 判断是不是第五列单元格,(单元格下标从0开始的)
            if (columnIndex == 4) {
                String stringCellValue = cell.getStringCellValue();
                long cellValue = Long.parseLong(stringCellValue);
                //若是第5列单元格内容大于80，则将字体样式设置为红色
                if (cellValue > 80) {
                    Workbook workbook = writeSheetHolder.getSheet().getWorkbook();
                    CellStyle cellStyle = workbook.createCellStyle();
                    //设置单元格边框类型
                    cellStyle.setBorderTop(BorderStyle.THIN);
                    cellStyle.setBorderBottom(BorderStyle.THIN);
                    cellStyle.setBorderLeft(BorderStyle.THIN);
                    cellStyle.setBorderRight(BorderStyle.THIN);
                    //水平居中
                    cellStyle.setAlignment(HorizontalAlignment.CENTER);
                    Font font = workbook.createFont();
                    //字体设置为红色
                    font.setColor(IndexedColors.RED.index);
                    cellStyle.setFont(font);
                    cell.setCellStyle(cellStyle);
                }
            }
        }
    }
}