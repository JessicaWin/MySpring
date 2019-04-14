package com.jessica.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jessica.dao.ItemDao;
import com.jessica.entity.Item;
import com.jessica.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemDao itemDao;

	@Override
	@Transactional
	public List<Item> selectAll() {
		return itemDao.selectAll();
	}

	@Override
	@Transactional
	public int insertItem(Item item) {
		return itemDao.insertItem(item);
	}

}
