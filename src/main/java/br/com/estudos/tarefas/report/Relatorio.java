package br.com.estudos.tarefas.report;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import br.com.estudos.tarefas.exception.TarefaException;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
import net.sf.jasperreports.export.XlsxReportConfiguration;

public class Relatorio {
	
	protected byte[] gerarPDF(String jasper, Map<String, Object> params, JRBeanCollectionDataSource dados) {
		try {
			informarParametrosGerais(params);
			return JasperExportManager.exportReportToPdf(getJasperPrint(jasper, params, dados));
		}catch(JRException e) {
			throw new TarefaException("Ocorreo um erro ao gerar o relatório",e);
		}
	}
	
	protected byte[] gerarXLS(String jasper, Map<String, Object> params, JRBeanCollectionDataSource dados) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			informarParametrosGerais(params);
			
			JRXlsxExporter exporter = new JRXlsxExporter();
			
			exporter.setExporterInput(new SimpleExporterInput(getJasperPrint(jasper, params, dados)));
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(out));
			exporter.setConfiguration(getConfiguracaoXLS());
			exporter.exportReport();
			
		}catch(JRException e) {
			throw new TarefaException("Ocorreo um erro ao gerar o relatório",e);
		}
		return out.toByteArray();
	}
	
	private Map<String, Object> informarParametrosGerais(Map<String, Object> params){
		if(params!=null) {
			params.put("REPORT_LOCALE", new Locale("pt","BR"));
			return params;
		}else {
			Map<String, Object> newParams = new HashMap<String, Object>();
			newParams.put("REPORT_LOCALE", new Locale("pt","BR"));
			return newParams;
		}
		
	}
	
	private JasperPrint getJasperPrint(String jasper, Map<String, Object> params, JRBeanCollectionDataSource dados) throws JRException{
		InputStream inputStream = this.getClass().getResourceAsStream(jasper);
		return JasperFillManager.fillReport(inputStream, params, dados);
	}
	
	private XlsxReportConfiguration getConfiguracaoXLS() {
		SimpleXlsxReportConfiguration config = new SimpleXlsxReportConfiguration();
		config.setOnePagePerSheet(Boolean.valueOf(false));
		config.setDetectCellType(Boolean.valueOf(false));
		config.setCollapseRowSpan(Boolean.valueOf(false));
		config.setWhitePageBackground(Boolean.valueOf(false));
		config.setIgnoreCellBackground(Boolean.valueOf(false));
		config.setRemoveEmptySpaceBetweenColumns(Boolean.valueOf(false));
		config.setRemoveEmptySpaceBetweenRows(Boolean.valueOf(false));
		return config;
	}
	
	

}
