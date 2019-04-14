package com.jessica.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jessica.entity.Item;
import com.jessica.service.ItemService;
import com.jessica.vo.ItemQueryVo;

@Controller
public class ItemController {
	@Autowired
	private ItemService itemService;

	@RequestMapping("queryAllItem")
	public String queryAllItem(HttpServletRequest request) {
		List<Item> itemList = itemService.selectAll();
		request.setAttribute("itemList", itemList);
		return "item/item-list";
	}

	@RequestMapping("/queryItems")
	public String queryItems(ItemQueryVo vo, HttpServletRequest request) {
		List<Item> itemList = this.itemService.selectByName(vo.getItem().getName());
		request.setAttribute("itemList", itemList);
		return "item/item-list";
	}

	@RequestMapping("queryItem/{id}")
	public String queryItemById(@PathVariable(name = "id") int id, HttpServletRequest request) {
		Item item = itemService.selectItemById(id);
		request.setAttribute("item", item);
		// 设置视图(逻辑路径)
		return "item/item-edit";
	}

	@RequestMapping("itemEdit")
	public String showEditItemById(@RequestParam(name = "id") int id, HttpServletRequest request) {
		Item item = itemService.selectItemById(id);
		request.setAttribute("item", item);
		// 设置视图(逻辑路径)
		return "item/item-edit";
	}

	@RequestMapping("updateItem")
	public String updateItem(Item item, HttpServletRequest request) {
		itemService.updateItem(item);
		// 设置视图(逻辑路径)
		return "item/success";
	}
}
