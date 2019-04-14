package com.jessica.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {
	private Integer id;

	private String name;

	private Float price;

	private String pic;

	private Date createtime;

	private String detail;

	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", price=" + price + ", pic=" + pic + ", createtime=" + createtime
				+ ", detail=" + detail + "]";
	}

}