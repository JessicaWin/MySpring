package com.jessica.dao;

import java.util.List;

import com.jessica.entity.Item;

public interface ItemDao {
	List<Item> selectAll();

	int insertItem(Item item);
}
