package com.jessica.service;

import java.util.List;

import com.jessica.entity.Item;

public interface ItemService {
	List<Item> selectAll();

	List<Item> selectByName(String name);

	Item selectItemById(int id);

	int insertItem(Item item);

	int updateItem(Item item);
}
