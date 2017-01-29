package com.jpmcuk;

public class Sale {
	int totalCount ;
	long totalValue;
	
	public Sale(int totalCount, int totalValue) {
		this.totalCount = totalCount;
		this.totalValue = totalValue;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public long getTotalValue() {
		return totalValue;
	}
	public void setTotalValue(long totalValue) {
		this.totalValue = totalValue;
	}
}