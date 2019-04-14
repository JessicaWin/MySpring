package com.jessica.service;

import java.util.Date;
import java.util.List;

import com.jessica.entity.Item;

public interface ItemService {
	List<Item> selectAll();

	List<Item> selectByName(String name);

	Item selectItemById(int id);

	List<Item> selectItemByIdArray(int[] idArray);

	List<Item> selectItemByIdList(List<Integer> idList);

	List<Item> selectItemByDate(Date date);

	int insertItem(Item item);

	int updateItem(Item item);
}
