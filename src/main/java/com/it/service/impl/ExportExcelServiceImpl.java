package com.it.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.fill.FillConfig;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteTableHolder;
import com.alibaba.fastjson.JSON;
import com.it.handler.CellWriteHandler;
import com.it.pojo.StudentInfo;
import com.it.service.ExportExcelService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.*;

@Service
public class ExportExcelServiceImpl implements ExportExcelService {

    private static Random random = new Random();

    @Override
    public void exportStudentInfoExcel(HttpServletResponse response) throws IOException {
        try {
            export(response);
        } catch (IOException e) {
            // 重置response
            response.reset();
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            Map<String, String> map = new HashMap<String, String>();
            map.put("status", "failure");
            map.put("message", "下载文件失败" + e.getMessage());
            response.getWriter().println(JSON.toJSONString(map));
        }
    }

    private void export(HttpServletResponse response) throws IOException {
        String templatePath = ExportExcelServiceImpl.class.getResource("/templates/score.xlsx").toString().replace("file:/","");
//        String templatePath="D:\\workspace\\springnoot\\spring\\src\\main\\resources\\templates\\score.xlsx";
        List<StudentInfo> list = getStudentInfoList();

        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode能够防止中文乱码 固然和easyexcel没有关系
        String fileName = URLEncoder.encode("月考成绩.xlsx", "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");

        ExcelWriter excelWriter = EasyExcel
                .write(response.getOutputStream())
                .registerWriteHandler(new CellWriteHandler()) // 自定义拦截器
                .withTemplate(templatePath)
                .autoCloseStream(Boolean.FALSE) // 这里须要设置不关闭流
                .build();
        WriteSheet writeSheet = EasyExcel.writerSheet("test").build();

        FillConfig fillConfig = FillConfig.builder().forceNewRow(Boolean.TRUE).build();
        excelWriter.fill(list,fillConfig, writeSheet);
        //设置填充内容
        Map<String,String> map = new HashMap<>();
        map.put("grade","高三10班");
        map.put("key","说明");
        map.put("value","* 英语成绩达到80分以上才算优秀");
        excelWriter.fill(map, writeSheet);

        excelWriter.finish();
    }

    /** * @return 随机生成学生成绩列表 */
    private List<StudentInfo> getStudentInfoList(){
        List<StudentInfo> list = new LinkedList<>();
        for (int i = 0; i < 15; i++) {
            StudentInfo studentInfo = new StudentInfo();
            studentInfo.setName("张三" + i);
            //随机生成450-600之间的总分
            int totalScore = random.nextInt(151) + 450;
            studentInfo.setTotalScore(String.valueOf(totalScore));
            //随机生成成绩在90-150之间的分数
            int englishScore = random.nextInt(61) + 90;
            studentInfo.setEnglishScore(String.valueOf(englishScore));
            list.add(studentInfo);
        }
        return orderByTotalScoreDesc(list);
    }

    private List<StudentInfo> orderByTotalScoreDesc(List<StudentInfo> list){
        list.sort(((o1, o2) -> Integer.parseInt(o2.getTotalScore()) - Integer.parseInt(o1.getTotalScore())));
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setNum(String.valueOf(i + 1));
        }
        return list;
    }
}