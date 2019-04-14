package com.jessica.vo;

import java.util.List;

import com.jessica.entity.Item;

import lombok.Data;

@Data
public class ItemQueryVo {
	private Item item;

	private List<Item> itemList;
}
