package com.jessica.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jessica.dao.ItemDao;
import com.jessica.entity.Item;
import com.jessica.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemDao itemDao;

	@Override
	public List<Item> selectAll() {
		return itemDao.selectAll();
	}

	@Override
	public int insertItem(Item item) {
		return itemDao.insertItem(item);
	}

	@Override
	public Item selectItemById(int id) {
		return itemDao.selectItemById(id);
	}

	@Override
	public int updateItem(Item item) {
		return itemDao.updateItem(item);
	}

	@Override
	public List<Item> selectByName(String name) {
		return itemDao.selectByName(name);
	}

	@Override
	public List<Item> selectItemByIdArray(int[] idArray) {
		return itemDao.selectItemByIdArray(idArray);
	}

	@Override
	public List<Item> selectItemByIdList(List<Integer> idList) {
		return itemDao.selectItemByIdList(idList);
	}

	@Override
	public List<Item> selectItemByDate(Date date) {
		return itemDao.selectItemByDate(date);
	}

}
