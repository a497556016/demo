package com.example.demo.mapper;

import java.util.List;

public interface MyMapper<T> {
	public T selectByPriKey(Object id);
	
	public List<T> selectAll();
}
