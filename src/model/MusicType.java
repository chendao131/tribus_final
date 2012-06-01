package model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "music_type")
public class MusicType implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6287404795448690321L;
	private Integer typeId;
	private String typeName;
	@Id
	public Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
}
