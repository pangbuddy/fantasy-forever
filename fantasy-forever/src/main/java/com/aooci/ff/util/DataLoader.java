package com.aooci.ff.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

public class DataLoader {
    private static Logger log = Logger.getLogger(DataLoader.class);
    
    public final static String BINARY_RESULT_EXTENSION = "_binary";

	public static Map<String, boolean[]> loadBinaryResults(String sourceResultFile, char separator, int dimension, int firstLine, int firstRow, int lastRow) {
		
		Map<String, boolean[]> results = null;
		try {
			results = new LinkedHashMap<String, boolean[]>();
		
			CSVReader reader = new CSVReader(new FileReader(sourceResultFile), separator, '\0', firstLine);
			CSVWriter writer = new CSVWriter(new FileWriter(sourceResultFile.replaceAll(".csv", BINARY_RESULT_EXTENSION + ".csv")), '\t', '\0');
			
			String[] rowToWrite = new String[dimension+1];
			rowToWrite[0] = "date";
			for(int i=0;i<dimension;i++){
				rowToWrite[i+1]=i+1+"";
			}
			writer.writeNext(rowToWrite);
			
			String [] nextLine;
		    while ((nextLine = reader.readNext()) != null) {
		    	boolean[] row = new boolean[dimension];
		    	for(int i=firstRow;i<lastRow;i++){
		    		row[Integer.parseInt(nextLine[i])-1] = true;
		    	}
		    	results.put(nextLine[0], row);
		    	rowToWrite[0] = nextLine[0];
		    	for(int i=0;i<dimension;i++){
		    		rowToWrite[i+1] = String.valueOf(row[i]);
		    	}
		    	writer.writeNext(rowToWrite);
		    }
		    reader.close();
		    writer.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			log.error(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
		return results;
	}

}
