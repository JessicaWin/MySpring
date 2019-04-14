package com.jessica.service;

import java.util.List;

import com.jessica.entity.Item;

public interface ItemService {
	List<Item> selectAll();

	int insertItem(Item item);
}
