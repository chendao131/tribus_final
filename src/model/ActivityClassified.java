package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="activity_classified")
public class ActivityClassified {
	private int classfiedId;
	private String classifiedName;

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getClassfiedId() {
		return classfiedId;
	}

	public void setClassfiedId(int classfiedId) {
		this.classfiedId = classfiedId;
	}

	public String getClassifiedName() {
		return classifiedName;
	}

	public void setClassifiedName(String classifiedName) {
		this.classifiedName = classifiedName;
	}
}
