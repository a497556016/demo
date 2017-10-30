package com.weishop.service;

import com.weishop.dto.ModelSizeDTO;
import com.weishop.dto.ShopCartDTO;
import com.weishop.pojo.ProductItem;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author HeShaowei
 * @since 2017-10-27
 */
public interface IProductItemService extends IService<ProductItem> {

	ModelSizeDTO selectModelSizeByProduct(String code);

	ShopCartDTO putInCart(String code, String model, String size, int count, Integer userId);
	
}
