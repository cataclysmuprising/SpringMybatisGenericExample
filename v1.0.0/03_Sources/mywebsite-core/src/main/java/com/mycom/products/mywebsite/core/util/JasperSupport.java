package com.mycom.products.mywebsite.core.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Map;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mycom.products.mywebsite.core.exception.BusinessException;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.OutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;

@Component
public class JasperSupport {
	public static final String REPORT_TRANSFER_LIST_FILE = "transfer_reports.jasper";
	public static final String REPORT_TRANSFER_ACCEPTANCE_LIST_FILE = "transfer_acceptance_form.jasper";
	public static final String REPORT_ISSUE_LIST_FILE = "issue_reports.jasper";
	public static final String REPORT_ISSUE_ACCEPTANCE_LIST_FILE = "issue_acceptance_form.jasper";
	public static final String REPORT_FA_STATUS_LIST = "fa_list_by_status_report.jasper";
	public static final String REPORT_FA_STOCK_TAKING_LIST = "fa_stock_taking_report.jasper";
	public static final String REPORT_FA_SUMMARY_REPORT_FILE = "fa_summary_report.jasper";
	public static final String REPORT_FA_SUMMARY_AUDIT_REPORT_FILE = "fa_summary_audit_report.jasper";
	public static final String REPORT_COST_DEPRECIATION_LIST = "cost_depreciation_reports.jasper";
	public static final String REPORT_BARCODE = "asset_barcode.jasper";

	@Autowired
	private DataSource dataSource;

	public enum DocType {
		EXCEL("excel"), PDF("pdf");
		private String value;

		DocType(String value) {
			this.value = value;
		}

		public String getValue() {
			return this.value;
		}
	}

	public byte[] generateReport(Map<String, Object> parameters, DocType docType,
			String fileName) throws BusinessException {
		byte[] stream = null;
		switch (docType) {
		case EXCEL: {
			try {
				stream = generateExcelReport(parameters, getInputStreamFromJasperFile(fileName));
			} catch (IOException | JRException | SQLException e) {
				throw new BusinessException("Generating Excel File got error !", e);
			}
			break;
		}
		case PDF: {
			try {
				stream = generatePDFReport(parameters, getInputStreamFromJasperFile(fileName));
			} catch (JRException | SQLException | IOException e) {
				throw new BusinessException("Generating PDF File got error !", e);
			}
			break;
		}
		default: {
			return stream;
		}
		}
		return stream;
	}

	public String tagReport(String string) {
		return string + "(" + Calendar.DAY_OF_MONTH + "-" + Calendar.MONTH + "-" + Calendar.YEAR + ")";
	}

	private byte[] generatePDFReport(Map<String, Object> parameters,
			InputStream is) throws JRException, SQLException, IOException {
		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(is);
		return JasperRunManager.runReportToPdf(jasperReport, parameters, dataSource.getConnection());
	}

	private byte[] generateExcelReport(Map<String, Object> parameters,
			InputStream is) throws IOException, JRException, SQLException {
		JasperPrint jasperPrint = JasperFillManager.fillReport(is, parameters, dataSource.getConnection());
		JRXlsxExporter exporter = new JRXlsxExporter();
		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		OutputStreamExporterOutput exporterOutput = new SimpleOutputStreamExporterOutput(os);
		exporter.setExporterOutput(exporterOutput);
		SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
		configuration.setOnePagePerSheet(true);
		configuration.setRemoveEmptySpaceBetweenRows(true);
		configuration.setRemoveEmptySpaceBetweenColumns(true);
		configuration.setWhitePageBackground(false);
		configuration.setWrapText(false);
		exporter.setConfiguration(configuration);
		exporter.exportReport();
		return os.toByteArray();
	}

	private InputStream getInputStreamFromJasperFile(String fileName) {
		return getClass().getResourceAsStream("/reports/" + fileName);
	}
}
