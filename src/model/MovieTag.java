package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "movie_tag")
public class MovieTag{
	private int tagId;
	private String tagName;
	@Id
	@GeneratedValue
	public int getTagId() {
		return tagId;
	}
	public void setTagId(int tagId) {
		this.tagId = tagId;
	}
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
}
