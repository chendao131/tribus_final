package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "zodiac")
public class Zodiac {
	private int zodiacId;
	private String zodiacName;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getZodiacId() {
		return zodiacId;
	}
	public void setZodiacId(int zodiacId) {
		this.zodiacId = zodiacId;
	}
	public String getZodiacName() {
		return zodiacName;
	}
	public void setZodiacName(String zodiacName) {
		this.zodiacName = zodiacName;
	}
}
