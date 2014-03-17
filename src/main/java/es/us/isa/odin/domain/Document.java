package es.us.isa.odin.domain;


import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

import es.us.isa.odin.annotations.EditedDate;
import es.us.isa.odin.annotations.PostPersist;
import es.us.isa.odin.annotations.PrePersist;


public class Document<T> {
	
	@Id
	private String id;
	@Version
	private Long version;
	@CreatedDate
	private DateTime creationDate;
	@EditedDate
	private DateTime lastEdit;
	private T entity;
	private Map<String, Object> extraData;
	
	public Document(T entity) {
		extraData = new HashMap<>();
		this.entity = entity;
	}
	
	public Document() {
		extraData = new HashMap<>();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long revision) {
		this.version = revision;
	}

	public DateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(DateTime creationDate) {
		this.creationDate = creationDate;
	}

	public DateTime getLastEdit() {
		return lastEdit;
	}

	public void setLastEdit(DateTime lastEdit) {
		this.lastEdit = lastEdit;
	}

	public T getEntity() {
		return entity;
	}

	public void setEntity(T entity) {
		this.entity = entity;
	}

	public Map<String, Object> getExtraData() {
		return extraData;
	}

	public void setExtraData(Map<String, Object> extraData) {
		this.extraData = extraData;
	}
	
	public Object addExtraData(String key, Object value) {
		return this.extraData.put(key, value);
	}
	
	public Object removeExtraData(String key) {
		return this.extraData.remove(key);
	}
	
	@Override
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		}

		if (this.id == null || obj == null || !(this.getClass().equals(obj.getClass()))) {
			return false;
		}

		Document<?> that = (Document<?>) obj;

		return this.id.equals(that.getId());
	}
	
	@PrePersist
	public void prePersist() {
		System.out.println("PRE DOC");
	}
	
	@PostPersist
	public void postPersist() {
		System.out.println("POST DOC");
	}
	
}
