package com.example.pizza;
public interface MenuItem extends Comparable<MenuItem> {
	public Double getPrice(); 
	public String toNiceString();

	public default int compareTo(MenuItem otherItem) {
		return (this.getPrice() > otherItem.getPrice()) ? 
		-1 : 
		( (this.getPrice() == otherItem.getPrice()) ? 
			0 : 
			1);
	}
}
