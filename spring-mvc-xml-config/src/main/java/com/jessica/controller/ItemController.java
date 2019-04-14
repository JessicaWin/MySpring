package com.jessica.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jessica.entity.Item;
import com.jessica.service.ItemService;

@Controller
public class ItemController {
	@Autowired
	private ItemService itemService;

	// @RequestMapping此时填写的是url
	// ModelAndView:model表示的是数据模型，view就是最终要展示给用户的视图
	@RequestMapping("queryItem")
	public ModelAndView queryItem() {
		// 查询数据库，用静态数据模拟
		List<Item> itemList = itemService.selectAll();
		ModelAndView mvAndView = new ModelAndView();
		// 设置数据模型,相当于request的setAttribute方法，实质上，底层确实也是转成了request（暂时这样理解）
		// 先将k/v数据放入map中，最终根据视图对象不同，再进行后续处理
		mvAndView.addObject("itemList", itemList);
		// 设置视图(逻辑路径)
		mvAndView.setViewName("item/item-list");
		return mvAndView;
	}
}
