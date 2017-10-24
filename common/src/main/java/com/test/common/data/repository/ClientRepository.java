package com.test.common.data.repository;

import com.test.common.data.entity.ClientEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ls on 8/22/16.
 */

@Repository
public interface ClientRepository extends BaseRepository<ClientEntity, Long> {

	ClientEntity findByUsername(String username);

	ClientEntity findById(Long id);


}

