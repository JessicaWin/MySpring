package com.jessica.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.jessica.entity.Item;
import com.jessica.exception.BusinessException;
import com.jessica.service.ItemService;
import com.jessica.vo.ItemQueryVo;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
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

	@RequestMapping("/queryItemByIdList")
	public String queryItemByIdList(int[] id, HttpServletRequest request) {
		List<Item> itemList = this.itemService.selectItemByIdArray(id);
		request.setAttribute("itemList", itemList);
		return "item/item-list";
	}

	@RequestMapping("/queryItemByPojoList")
	public String queryItemByPojoList(ItemQueryVo vo, HttpServletRequest request) {
		List<Integer> ids = vo.getItemList().stream().map(Item::getId).collect(Collectors.toList());
		List<Item> itemList = this.itemService.selectItemByIdList(ids);
		request.setAttribute("itemList", itemList);
		return "item/item-list";
	}

	@RequestMapping("/queryItemByDate")
	public String queryItemByDate(Date date, HttpServletRequest request) {
		List<Item> itemList = this.itemService.selectItemByDate(date);
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
	public String showEditItemById(@RequestParam(name = "id") int id, HttpServletRequest request) throws Exception {
		Item item = itemService.selectItemById(id);
		if (item == null) {
			throw new BusinessException("Can not find item");
		}
		request.setAttribute("item", item);
		// 设置视图(逻辑路径)
		return "item/item-edit";
	}

	@RequestMapping(value = "updateItem", produces = "application/json; charset=UTF-8")
	public String updateItem(Item item, MultipartFile pictureFile, HttpServletRequest request) throws Exception {
		if (pictureFile != null) {
			String originalFileName = pictureFile.getOriginalFilename();
			log.debug(pictureFile.getOriginalFilename());
			if (originalFileName != null && originalFileName.length() > 0) {
				String filePath = "/Users/jessica/upload/";
				File newFile = new File(filePath + originalFileName);
				try {
					pictureFile.transferTo(newFile);
					item.setPic(originalFileName);
				} catch (IllegalStateException | IOException e) {
					log.debug(e);
					throw new BusinessException("Upload fail");
				}
			}
		} else {
			throw new BusinessException("File name not exist");
		}
		try {
			itemService.updateItem(item);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		// 设置视图(逻辑路径)
		return "success";
	}
}
