package com.test.common.utils;

import org.springframework.data.domain.Sort;

import java.util.Iterator;

/**
 * Created by ls on 2/20/17.
 */
public class SortUtils {

	// Todo find better https://github.com/thymeleaf/thymeleaf-spring/issues/19
	public static final String getSortParam(Sort sort){
		if(sort == null){
			return null;
		}
		Iterator<Sort.Order> orderIterator = sort.iterator();
		while(orderIterator.hasNext()){
			Sort.Order order = orderIterator.next();
			return order.getProperty()+","+order.getDirection();
		}
		return null;
	}
}
