package com.weishop.service.impl;

import com.weishop.pojo.ShopCart;
import com.weishop.mapper.ShopCartMapper;
import com.weishop.service.IShopCartService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 购物车 服务实现类
 * </p>
 *
 * @author HeShaowei
 * @since 2017-10-27
 */
@Service
public class ShopCartServiceImpl extends ServiceImpl<ShopCartMapper, ShopCart> implements IShopCartService {
	
}
