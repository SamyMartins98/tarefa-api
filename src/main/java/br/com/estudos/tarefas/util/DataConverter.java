package br.com.estudos.tarefas.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import org.springframework.util.StringUtils;

public class DataConverter {
	
	private static DecimalFormat decimalFormat = null;
	
	
	static {
		DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator('.');
        symbols.setDecimalSeparator(',');
        
        decimalFormat = new DecimalFormat("#,##0.00", symbols);
        decimalFormat.setParseBigDecimal(true);
       
	}
	
	public static String castToString(Object value) {
		if (!Objects.isNull(value))
			return (String) value;
		return "";
	}

	public static String removerCaracteres(String doc) {
		if (!Objects.isNull(doc))
			return doc.trim().replaceAll("[,./-]", "").replace(" ", "");

		return doc;
	}

    public static String formataValor(BigDecimal valor) {
    	if(!Objects.isNull(valor))
           return decimalFormat.format(valor);
    	return "";
    }
    
    public static Long stringToLong(String valor) {
    	if(!StringUtils.isEmpty(valor))
           return Long.parseLong(valor);
    	return null;
    }
    
    public static String localDateToString(LocalDate data, String padrao){
        if(!Objects.isNull(data)) {
        	 DateTimeFormatter localDateFormat = DateTimeFormatter.ofPattern(padrao);
           return localDateFormat.format(data);
        }
     
        return "";
    }
    
    public static String localDateTimeToString(LocalDateTime data, String padrao){
        if(!Objects.isNull(data)) {
        	 DateTimeFormatter localDateFormat = DateTimeFormatter.ofPattern(padrao);
           return localDateFormat.format(data);
        }
     
        return "";
    }
    
    
    public static LocalDate stringUTCToLocalDate(String data){
        if(!Objects.isNull(data)) {
        	OffsetDateTime odt = OffsetDateTime.parse(data);
    		return odt.toLocalDate();
        }
     
        return null;
    }
    
    
    public static String longToString(Long valor) {
    	if(!Objects.isNull(valor)) {
    		return valor.toString(); 
    	}
    	return "";
    }
    
    public static String localDateTimeToStringUTC(LocalDateTime data){
        if(!Objects.isNull(data)) {
        	OffsetDateTime odt = OffsetDateTime.of(data, ZoneOffset.UTC);
    		return odt.toString();
        }
     
        return null;
    }
    
    
    public static LocalDateTime dataIuguLogToLocalDateTime(String data) {
		Integer dia = Integer.parseInt(data.substring(0,2));
		Integer mes = Integer.parseInt(data.substring(3,5));
		Integer ano = Integer.parseInt("2019");
		Integer hora = Integer.parseInt(data.substring(7,9));
		Integer min = Integer.parseInt(data.substring(10,12));
		
		LocalDateTime dataEmissao = LocalDateTime.of(LocalDate.of(ano, mes, dia), LocalTime.of(hora, min));
		
	    return dataEmissao;	
	}
}
