package com.test.common.data.entity;

import javax.persistence.*;

/**
 * Created by ls on 8/20/16.
 */

@Entity
@Table(name = "clients")
public class ClientEntity extends UserEntity {


	public ClientEntity() {}

	public ClientEntity(Long id) {
		this.id = id;
	}


}
