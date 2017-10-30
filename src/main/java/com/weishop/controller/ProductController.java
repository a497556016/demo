package com.weishop.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.google.common.collect.Lists;
import com.weishop.base.BaseController;
import com.weishop.base.BaseResponse;
import com.weishop.base.PageResponse;
import com.weishop.dto.ProductDTO;
import com.weishop.global.BusType;
import com.weishop.global.ResponseCode;
import com.weishop.pojo.CommonFile;
import com.weishop.pojo.Product;
import com.weishop.pojo.ProductItem;
import com.weishop.properties.FileServerProperties;
import com.weishop.service.ICommonFileService;
import com.weishop.service.IProductItemService;
import com.weishop.service.IProductService;
import com.weishop.utils.PropertyUtils;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * <p>
 * 商品 前端控制器
 * </p>
 *
 * @author HeShaowei
 * @since 2017-10-17
 */
@RestController
@RequestMapping("//product")
public class ProductController extends BaseController {
	@Autowired
	private IProductService productService;
	@Autowired
	private ICommonFileService commonFileService;
	@Autowired
	private FileServerProperties fileServerProperties;
	
	@RequestMapping("/listProduct")
	@ResponseBody
	public PageResponse<ProductDTO> listProduct(int current,int size){
		Page<Product> page = productService.selectPage(new Page<>(current, size));
		
		PageResponse<ProductDTO> pageResponse = PropertyUtils.convertModelToDTO(page,ProductDTO.class);
		for(ProductDTO product : pageResponse.getRows()) {
			EntityWrapper<CommonFile> wrapper = new EntityWrapper<>();
			wrapper.eq(CommonFile.BUS_TYPE, BusType.PRODUCT.getType());
			wrapper.eq(CommonFile.BUS_ID, product.getId());
			CommonFile cf = commonFileService.selectOne(wrapper);
			if(null!=cf) {
				product.setPicUrl(fileServerProperties.getUrl()+cf.getFilePath());
			}
		}
		pageResponse.setCode(ResponseCode.SUCCESS.getCode());
		return pageResponse;
	}
	
}
