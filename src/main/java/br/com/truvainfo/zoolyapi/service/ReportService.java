package br.com.truvainfo.zoolyapi.service;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

import static br.com.truvainfo.zoolyapi.util.GeneralUtil.getMessage;
import static java.util.Objects.*;

@Service
public class ReportService {
	
	private static String MSG_ERROR_GENERATE_REPORT = "msg.error.generate.report";
	
	@Value("${spring.datasource.driver-class-name}")
	private String CONNECTION_DRIVER;
	
	@Value("${spring.datasource.url}")
	private String CONNECTION_URL;
	
	@Value("${spring.datasource.username}")
	private String CONNECTION_USER;
	
	@Value("${spring.datasource.password}")
	private String CONNECTION_PASSWORD;
	
	private static final String MIME_TYPE = "application/pdf";
	
	public void exportToPdfStream(final String filePath, final String fileName,
	                              final Map<String, Object> params,
	                              final HttpServletResponse response) {
		
		try {
			
			final InputStream reportStream = this.getClass().getResourceAsStream(filePath);
			final JasperDesign jasperDesign = JRXmlLoader.load(reportStream);
			final JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
			final JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, new JREmptyDataSource());
			
			response.setContentType(MIME_TYPE);
			response.setHeader("content-disposition", "attachment; filename=" + fileName);
			
			final ServletOutputStream outputStream = response.getOutputStream();
			
			if (nonNull(outputStream)) {
				
				JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
			}
			
		} catch (Exception ex) {
			
			throw new RuntimeException(getMessage(MSG_ERROR_GENERATE_REPORT) + ex.getCause());
		}
	}
}
