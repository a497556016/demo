package com.weishop.service.impl;

import com.weishop.pojo.Product;
import com.weishop.pojo.ProductItem;
import com.weishop.pojo.ShopCart;
import com.weishop.dto.ModelSizeDTO;
import com.weishop.dto.ShopCartDTO;
import com.weishop.mapper.ProductItemMapper;
import com.weishop.mapper.ProductMapper;
import com.weishop.mapper.ShopCartMapper;
import com.weishop.service.IProductItemService;
import com.weishop.utils.PropertyUtils;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author HeShaowei
 * @since 2017-10-27
 */
@Service
public class ProductItemServiceImpl extends ServiceImpl<ProductItemMapper, ProductItem> implements IProductItemService {
	@Autowired
	private ProductMapper productMapper;
	@Autowired
	private ShopCartMapper shopCartMapper;
	@Override
	public ModelSizeDTO selectModelSizeByProduct(String code) {
		ModelSizeDTO modelSizeDTO = new ModelSizeDTO();
		List<String> models = this.baseMapper.selectModels(code);
		List<String> sizes = this.baseMapper.selectSizes(code);
		modelSizeDTO.setModel(models);
		modelSizeDTO.setSize(sizes);
		return modelSizeDTO;
	}

	@Override
	public ShopCartDTO putInCart(String code, String model, String size,int count, Integer userId) {
		ShopCartDTO shopCartDTO = new ShopCartDTO();
		
		EntityWrapper<ProductItem> wrapper = new EntityWrapper<>();
		wrapper.eq(ProductItem.P_CODE, code);
		wrapper.eq(ProductItem.MODEL, model);
		wrapper.eq(ProductItem.SIZE, size);
		List<ProductItem> productItems = this.baseMapper.selectList(wrapper);
		if(CollectionUtils.isNotEmpty(productItems)) {
			ProductItem productItem = productItems.get(0);
			if(productItem.getStoreTotal()-count<=0) {
				throw new RuntimeException("抱歉，库存已经不足！");
			}else {
				Product product = new Product();
				product.setCode(code);
				product = productMapper.selectOne(product);
				ShopCart shopCart = new ShopCart();
				shopCart.setUserId(userId);
				shopCart.setCount(count);
				shopCart.setProItemId(productItem.getId());
				shopCart.setPrice(BigDecimal.valueOf(product.getPrice().doubleValue()*product.getDiscount().doubleValue()));
				PropertyUtils.convertModelToDTO(product, shopCartDTO);
				shopCartDTO.setProItemId(productItem.getId());
				shopCartDTO.setCount(count);
				shopCartDTO.setPrice(shopCart.getPrice());
				shopCartDTO.setUserId(userId);
				shopCartMapper.insert(shopCart);
			}
		}else {
			throw new RuntimeException("抱歉，该商品已经下架！");
		}
		return shopCartDTO;
	}
	
}
