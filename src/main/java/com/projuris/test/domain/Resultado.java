package com.projuris.test.domain;

public class Resultado {
	
	private Integer total_area;
	private Integer number_of_spots;
	private Double spots_average_area;
	private Integer biggest_spot_area;
	
	public Integer getNumber_of_spots() {
		return number_of_spots;
	}
	public void setNumber_of_spots(Integer number_of_spots) {
		this.number_of_spots = number_of_spots;
	}
	public Double getSpots_average_area() {
		return spots_average_area;
	}
	public void setSpots_average_area(Double spots_average_area) {
		this.spots_average_area = spots_average_area;
	}
	public Integer getBiggest_spot_area() {
		return biggest_spot_area;
	}
	public void setBiggest_spot_area(Integer biggest_spot_area) {
		this.biggest_spot_area = biggest_spot_area;
	}
	public Integer getTotal_area() {
		return total_area;
	}
	public void setTotal_area(Integer total_area) {
		this.total_area = total_area;
	}
	@Override
	public String toString() {
		return "Resultado [total_area=" + total_area + ", number_of_spots=" + number_of_spots + ", spots_average_area="
				+ spots_average_area + ", biggest_spot_area=" + biggest_spot_area + "]";
	}
}
