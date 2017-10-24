package com.test.common.data.service;

import com.test.common.data.entity.AbstractEntity;
import com.test.common.data.repository.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ls on 8/21/16.
 */
public abstract class AbstractDataService<T extends AbstractEntity, ID extends Serializable> implements DataService<T, ID> {

	protected BaseRepository<T, ID> repository;

	public AbstractDataService(BaseRepository<T, ID> repository) {
		this.repository = repository;
	}

	protected AbstractDataService() {
	}

	@Override
	public T save(T object) {
		return repository.save(object);
	}

	@Override
	public void remove(T object) {
		repository.delete(object);
	}

	@Override
	public void removeById(ID id) {
		repository.delete(id);
	}

	@Override
	public T findById(ID id) {
		return repository.findOne(id);
	}

	@Override
	public List<T> findAll() {
		return repository.findAll();
	}

	@Override
	public long count() {
		return repository.count();
	}

	@Override
	public List<T> findAll(Sort sort) {
		return repository.findAll(sort);
	}

	@Override
	public Page<T> findPageable(Pageable pageable) {
		return repository.findAll(pageable);
	}
}
