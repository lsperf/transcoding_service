package com.test.common.data.service;

import com.test.common.data.entity.ClientEntity;
import com.test.common.data.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ls on 8/22/16.
 */

@Service
public class ClientDataService extends AbstractDataService<ClientEntity, Long> {

	@Autowired
	public ClientDataService(ClientRepository clientRepository) {
		super(clientRepository);
	}

	@Autowired
	private ClientRepository clientRepository;

	public ClientEntity findByUsername(String username) {
		return clientRepository.findByUsername(username);
	}

}
