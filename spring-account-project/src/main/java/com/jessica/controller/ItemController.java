package com.jessica.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jessica.entity.Item;

@Controller
public class ItemController {
	// @RequestMapping此时填写的是url
	// ModelAndView:model表示的是数据模型，view就是最终要展示给用户的视图
	@RequestMapping("queryItem")
	public ModelAndView queryItem() {

		// 查询数据库，用静态数据模拟
		List<Item> itemList = new ArrayList<>();

		// 商品列表
		Item item_1 = new Item();
		item_1.setName("联想笔记本_3");
		item_1.setPrice(6000f);
		item_1.setDetail("ThinkPad T430 联想笔记本电脑！");

		Item item_2 = new Item();
		item_2.setName("苹果手机");
		item_2.setPrice(5000f);
		item_2.setDetail("iphone6苹果手机！");

		itemList.add(item_1);
		itemList.add(item_2);

		ModelAndView mvAndView = new ModelAndView();

		// 设置数据模型,相当于request的setAttribute方法，实质上，底层确实也是转成了request（暂时这样理解）
		// 先将k/v数据放入map中，最终根据视图对象不同，再进行后续处理
		mvAndView.addObject("itemList", itemList);

		// 设置视图(逻辑路径)
		mvAndView.setViewName("item/item-list");
		// mvAndView.setViewName("/WEB-INF/jsp/item/item-list.jsp");

		return mvAndView;
	}
}
