package com.weishop.service.impl;

import com.weishop.pojo.OrderList;
import com.weishop.mapper.OrderListMapper;
import com.weishop.service.IOrderListService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
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
public class OrderListServiceImpl extends ServiceImpl<OrderListMapper, OrderList> implements IOrderListService {
	
}
