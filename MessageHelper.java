package com.jpmcuk;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class MessageHelper {

	/**
	 * This method is use to log the report
	 * 
	 * @param salesMap
	 */
	public void logReport(Map<String, Sale> salesMap) {
		
		System.out.println("**************************************************************");
		System.out.println("No of Product: " + salesMap.size());
		System.out.println("--------------------------------------------------------------");
		for (Entry<String, Sale> entry : salesMap.entrySet()) {
			System.out.println("Product name: " + entry.getKey());
			System.out.println("Product total sales count: "
					+ entry.getValue().getTotalCount());
			System.out.println("Product total sales value: "
					+ entry.getValue().getTotalValue());
			System.out.println("--------------------------------------------------------------");
		}
		System.out.println("**************************************************************");
	}

	/**
	 * This method load the input message request from the file and create new
	 * list and return it.
	 * 
	 * @param fileName
	 * @return List<String>
	 * @throws Exception
	 */
	public List<String> getInputListFromFile(String fileName)
			throws Exception {

		List<String> inputList = new ArrayList<>();

		try {
			// Open the file.
			FileInputStream fis = new FileInputStream(fileName);
			// Get the object of DataInputStream
			DataInputStream dis = new DataInputStream(fis);
			BufferedReader br = new BufferedReader(new InputStreamReader(dis));
			String strLine = br.readLine();
			// Read File Line By Line
			while (strLine != null) {
				inputList.add(strLine);
				strLine = br.readLine();
			}
			// Close the input stream
			fis.close();
			dis.close();
		} catch (FileNotFoundException e) {
			throw new Exception(
					"File not found. File name or File path may be wrong. Please provide the right file name");
		} catch (Exception e) {// Catch exception if any
			throw e;
		}
		return inputList;
	}

}
