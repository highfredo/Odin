package us.es.isa.odin.domain;


import java.util.Map;
import java.util.UUID;
import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public abstract class AbstractDocument<T extends Entity> {
	
	@Id
	private UUID id;
	@Version
	private Long version;
	private DateTime creationDate;
	private DateTime lastEdit;
	private T entity;
	private Map<String, Object> extraData;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
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
	
}
