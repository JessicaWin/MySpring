package com.jessica.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jessica.entity.Item;

public interface ItemDao {
	List<Item> selectAll();

	Item selectItemById(int id);

	List<Item> selectByName(@Param(value = "name") String name);

	List<Item> selectItemByDate(@Param(value = "createtime") Date createtime);

	List<Item> selectItemByIdList(@Param(value = "list") List<Integer> idList);

	List<Item> selectItemByIdArray(@Param(value = "list") int[] idArray);

	int insertItem(Item item);

	int updateItem(Item item);
}
