package com.weishop.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.weishop.base.BaseController;
import com.weishop.base.BaseResponse;
import com.weishop.dto.ModelSizeDTO;
import com.weishop.dto.ShopCartDTO;
import com.weishop.pojo.ProductItem;
import com.weishop.pojo.ShopCart;
import com.weishop.service.IProductItemService;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author HeShaowei
 * @since 2017-10-27
 */
@RestController
@RequestMapping("//productItem")
public class ProductItemController extends BaseController {
	@Autowired
	private IProductItemService productItemService;
	
	@RequestMapping("/getProductItem")
	@ResponseBody
	public BaseResponse<List<ProductItem>> getProductItem(String pCode){
		EntityWrapper<ProductItem> wrapper = new EntityWrapper<>();
		wrapper.eq(ProductItem.P_CODE, pCode);
		List<ProductItem> productItems = productItemService.selectList(wrapper);
		return BaseResponse.result(productItems);
	}
	
	@RequestMapping("/selectModelSizeByProduct")
	@ResponseBody
	public BaseResponse<ModelSizeDTO> selectModelSizeByProduct(String code){
		ModelSizeDTO modelSizeDTO = productItemService.selectModelSizeByProduct(code);
		return BaseResponse.result(modelSizeDTO);
	}
	
	@RequestMapping("/putInCart")
	@ResponseBody
	public BaseResponse<ShopCartDTO> putInCart(String code,String model,String size,int count,Integer userId){
		ShopCartDTO shopCartDTO = productItemService.putInCart(code,model,size,count,userId);
		return BaseResponse.result(shopCartDTO);
	}
}
