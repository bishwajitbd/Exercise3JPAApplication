package javaClasses;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="CENSUSYEAR", schema="APP")
public class CensusYear {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="CENSUSYEARID")
    int censusYearID; 
	
	@Column(name="CENSUSYEAR")
    int censusYear;

	public CensusYear() {

	}

	//private Set<Age> ages= new HashSet<Age>();
	
	
	public int getCensusYearID() {
		return censusYearID;
	}

	public void setCensusYearID(int censusYearID) {
		this.censusYearID = censusYearID;
	}

	public int getCensusYear() {
		return censusYear;
	}

	public void setCensusYear(int censusYear) {
		this.censusYear = censusYear;
	}
	
	
}
