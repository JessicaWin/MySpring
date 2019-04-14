package com.jessica.service;

import java.util.List;

import com.jessica.entity.Item;

public interface ItemService {
	List<Item> selectAll();

	Item selectItemById(int id);

	int insertItem(Item item);

	int updateItem(Item item);
}
