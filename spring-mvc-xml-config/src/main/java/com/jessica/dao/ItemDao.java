package com.jessica.dao;

import java.util.List;

import com.jessica.entity.Item;

public interface ItemDao {
	List<Item> selectAll();

	Item selectItemById(int id);

	int insertItem(Item item);

	int updateItem(Item item);
}
