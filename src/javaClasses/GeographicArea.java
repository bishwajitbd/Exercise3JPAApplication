package javaClasses;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="GEOGRAPHICAREA", schema="APP")
public class GeographicArea {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="GEOGRAPHICAREAID")
    int geographicAreaID; 
	
	@Column(name="CODE")
    int code; 
	@Column(name="LEVEL")
    int level; 
	@Column(name="NAME")
    String name; 
	@Column(name="ALTERNATIVECODE")
    int alternativeCode;
	public GeographicArea() {

	}
	public int getGeographicAreaID() {
		return geographicAreaID;
	}
	public void setGeographicAreaID(int geographicAreaID) {
		this.geographicAreaID = geographicAreaID;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAlternativeCode() {
		return alternativeCode;
	}
	public void setAlternativeCode(int alternativeCode) {
		this.alternativeCode = alternativeCode;
	} 
	
	
    
    
	
}
