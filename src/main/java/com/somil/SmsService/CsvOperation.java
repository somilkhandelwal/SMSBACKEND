/**
 * 
 */
package com.somil.SmsService;

/**
 * @author Somil Khandelwal
 *
 *	All CSV Operation such as Reading and writing File
 */
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import bean.SentMessageBean;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class CsvOperation {

    private static final String SAMPLE_CSV_FILE_PATH = "data.csv";
    public static void setEntries (String mobileNumber, String countryCode, String firstname, String lastName, String randomOtp, String sid) throws IOException
    {
    	try (
    			FileWriter writer = new FileWriter("data.csv",true); 
    			CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT);
            ) {
	    		csvPrinter.getOut();
                csvPrinter.printRecord(sid,firstname+" "+ lastName, "+"+countryCode+mobileNumber, randomOtp,System.currentTimeMillis() / 1000L);
            }    	
    }
    
    public static ArrayList<SentMessageBean> getEntries () throws IOException 
    {
    	ArrayList<SentMessageBean> list=new ArrayList<SentMessageBean>();
    	if(!isFileExist())
    		throw new FileNotFoundException();
    	 try (
    	            Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));
    	            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
    	                    .withFirstRecordAsHeader()
    	                    .withIgnoreHeaderCase()
    	                    .withTrim());
    	        ) {
    	            for (CSVRecord csvRecord : csvParser) {
    	            	SentMessageBean sentBean = new SentMessageBean();
    	            	System.out.println(csvRecord.get("refNumber")+csvRecord.get("name")+csvRecord.get("mobileNumber")+csvRecord.get("otp"));
    	                sentBean.setId(csvRecord.get("refNumber"));
    	                sentBean.setName(csvRecord.get("name"));
    	                sentBean.setMobileNumber(csvRecord.get("mobileNumber"));
    	                sentBean.setOtp(csvRecord.get("otp"));
    	                sentBean.setTimeStamp(csvRecord.get("timeStamp"));
    	                
    	                list.add(sentBean);
    	            }
    	        }
    	 catch (Exception e) {
    		 throw new IOException();
		}
    	 
    	 return list;
    }
    public static boolean isFileExist () {
    	File f = new File(SAMPLE_CSV_FILE_PATH);
		return f.exists();
	}

}
