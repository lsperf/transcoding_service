package com.test.common.data.service;

import com.test.common.data.entity.AbstractEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ls on 8/21/16.
 */
public interface DataService<T extends AbstractEntity, ID extends Serializable> {
	T save(T object);

	void remove(T object);

	void removeById(ID id);

	T findById(ID id);

	List<T> findAll(Sort sort);

	List<T> findAll();

	Page<T> findPageable(Pageable pageable);

	long count();
}
