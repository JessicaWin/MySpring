package com.jessica.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jessica.entity.Item;

public interface ItemDao {
	List<Item> selectAll();

	Item selectItemById(int id);

	List<Item> selectByName(@Param(value = "name") String name);

	int insertItem(Item item);

	int updateItem(Item item);
}
