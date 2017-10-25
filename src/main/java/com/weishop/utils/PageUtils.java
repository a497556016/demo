package com.weishop.utils;

import java.util.List;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.PageHelper;

public class PageUtils {
	public static <T> Page<T> convertToPage(List<T> listData){
		Page<T> page = new Page<>();
		page.setTotal(PageHelper.freeTotal());
		page.setRecords(listData);
		return page;
	}
}
