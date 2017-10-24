package com.test.common.data.repository;

import com.test.common.data.entity.AbstractEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * Created by ls on 8/21/16.
 */

@NoRepositoryBean
public interface BaseRepository<T extends AbstractEntity, ID extends Serializable> extends JpaRepository<T, ID> {

}
