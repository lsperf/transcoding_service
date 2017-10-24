package com.test.common.data.entity;


import com.test.common.data.converter.LocalDateTimeAttributeConverter;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by ls on 8/20/16.
 */
@MappedSuperclass
public abstract class AbstractEntity<T> {

	@Convert(converter = LocalDateTimeAttributeConverter.class)
	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Convert(converter = LocalDateTimeAttributeConverter.class)
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	@PrePersist
	protected void onPrePersist() {
		this.createdAt = LocalDateTime.now();
		this.updatedAt = this.createdAt;
	}

	@PreUpdate
	protected void onPreUpdate() {
		this.updatedAt = LocalDateTime.now();
	}

	public abstract T getId();

	public abstract void setId(T id);

	@Override
	public boolean equals(Object obj) {
		T id = this.getId();
		return (id != null && id.equals(((AbstractEntity) obj).getId())) ?
				true : super.equals(obj);
	}
}
