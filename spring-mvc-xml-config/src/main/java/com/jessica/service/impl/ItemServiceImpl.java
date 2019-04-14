package com.jessica.service.impl;

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

}
