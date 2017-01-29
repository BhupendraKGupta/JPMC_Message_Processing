package com.jpmcuk;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageProcessing {

	public static void main(String[] args) throws Exception {

		// This map list use to store and process the sales
		Map<String, Sale> salesMap = new HashMap<String, Sale>();

		MessageHelper messageHelper = new MessageHelper();
		
		List<String> inputList = messageHelper.getInputListFromFile("./src/com/jpmcuk/Input.txt");

		int iteration = 0; // use to count the 50 message request
		int pause10 = 0; // use to log report after every 10th message request

		// iterating message request for recording and processing
		while (inputList.size() != iteration) {

			String input = inputList.get(iteration);

			iteration++;
			pause10++;

			if (pause10 == 10) {// if it is 10 then need to log report

				System.out.println("Report after every 10th message:");

				messageHelper.logReport(salesMap);

				pause10 = 0; // resetting to 0 for recount from initial
			}

			String[] inputs = input.split(" ");

			if ("Add".equalsIgnoreCase(inputs[0])) { // Operation Add
				// type 3
				int cost = Integer.parseInt(inputs[1].substring(0,
						inputs[1].length() - 1));

				Sale sale = salesMap.get(inputs[2].toUpperCase());
				if (sale != null) {
					sale.setTotalValue(sale.getTotalValue()
							+ sale.getTotalCount() * cost);
				}
			} else if ("subtract".equalsIgnoreCase(inputs[0])) { // operation subtract
				// type 3
				int cost = Integer.parseInt(inputs[1].substring(0,
						inputs[1].length() - 1));

				Sale sale = salesMap.get(inputs[2].toUpperCase());
				if (sale != null) {
					sale.setTotalValue(sale.getTotalValue()
							- sale.getTotalCount() * cost);
					
				}

			} else if ("multiply".equalsIgnoreCase(inputs[0])) { // operation multiply
				// type 3
				int cost = Integer.parseInt(inputs[1].substring(0,
						inputs[1].length() - 1));
				
				Sale sale = salesMap.get(inputs[2].toUpperCase());
				
				if (sale != null) {
					sale.setTotalValue(sale.getTotalValue()
							+ sale.getTotalCount() * cost);
				}
			} else {
				int count = 0;
				try {
					count = Integer.parseInt(inputs[0]);
					String name = inputs[3].toUpperCase();
					int cost = Integer.parseInt(inputs[5].substring(0,
							inputs[5].length() - 1));
					// if here, it means type2 input = "20 sales of apples at 10p each";

					Sale sale = salesMap.get(name);

					if (sale != null) {
						sale.setTotalCount(sale.getTotalCount() + count);
						sale.setTotalValue(sale.getTotalValue() + count * cost);
					} else {
						sale = new Sale(count, count * cost);
						salesMap.put(name, sale);
					}
					
				} catch (NumberFormatException nfe) {
					// if here, it means message type 1
					String name = inputs[0].toUpperCase();
					int cost = Integer.parseInt(inputs[2].substring(0,
							inputs[2].length() - 1));
					
					Sale sale = salesMap.get(name + "S");

					if (sale != null) {
						sale.setTotalCount(sale.getTotalCount() + 1);
						sale.setTotalValue(sale.getTotalValue() + cost);
					} else {
						sale = new Sale(1, cost);
						salesMap.put(name + "S", sale);
					}
				}
			}
			
			//Exit condition once system reached to 50 message processing
			if (iteration == 50) {
				System.out
						.println("We are stopping the message request now as system reached to maximum threshold limit i.e. 50");
				
				System.out.println("Total Record: " + iteration);
				
				messageHelper.logReport(salesMap);
				
				System.exit(0);
			}
		}

	}
	
}
