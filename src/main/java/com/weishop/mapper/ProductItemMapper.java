package com.weishop.mapper;

import com.weishop.pojo.ProductItem;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author HeShaowei
 * @since 2017-10-27
 */
public interface ProductItemMapper extends BaseMapper<ProductItem> {

	List<String> selectModels(String code);

	List<String> selectSizes(String code);

}