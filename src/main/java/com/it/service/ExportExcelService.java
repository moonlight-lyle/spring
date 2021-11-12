package com.it.service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ExportExcelService {

    void exportStudentInfoExcel(HttpServletResponse response) throws IOException;
}
