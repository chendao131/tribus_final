package model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "music_version")
public class MusicVersion implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5422750195425599769L;
	private Integer versionId;
	private String versionName;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getVersionId() {
		return versionId;
	}
	public void setVersionId(Integer versionId) {
		this.versionId = versionId;
	}
	public String getVersionName() {
		return versionName;
	}
	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}
}
