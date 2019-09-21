package javaClasses;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="HOUSEHOLD", schema="APP")
public class HouseHold {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
    int id;
	
	@ManyToOne
	@JoinColumn(name="GEOGRAPHICAREA")
    GeographicArea geographicArea; 
	
	@ManyToOne
	@JoinColumn(name="HOUSEHOLDTYPE")
    HouseholdType householdType;
	
	@ManyToOne
	@JoinColumn(name="HOUSEHOLDSIZE")
    HouseholdSize householdSize; 
	
	@ManyToOne
	@JoinColumn(name="HOUSEHOLDSBYAGERANGE")
    HouseholdsByAgeRange householdsByAgeRange;
	
	@ManyToOne
	@JoinColumn(name="HOUSEHOLDEARNERS")
    HouseholdEarners householdEarners; 
	
	@ManyToOne
	@JoinColumn(name="TOTALINCOME")
    TotalIncome totalIncome;
	
	@ManyToOne
	@JoinColumn(name="CENSUSYEAR")
    CensusYear censusYear;
    
    @Column(name="NUMBERREPORTED")
    int numberReported;

	public HouseHold() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public GeographicArea getGeographicArea() {
		return geographicArea;
	}

	public void setGeographicArea(GeographicArea geographicArea) {
		this.geographicArea = geographicArea;
	}

	public HouseholdType getHouseholdType() {
		return householdType;
	}

	public void setHouseholdType(HouseholdType householdType) {
		this.householdType = householdType;
	}

	public HouseholdSize getHouseholdSize() {
		return householdSize;
	}

	public void setHouseholdSize(HouseholdSize householdSize) {
		this.householdSize = householdSize;
	}

	public HouseholdsByAgeRange getHouseholdsByAgeRange() {
		return householdsByAgeRange;
	}

	public void setHouseholdsByAgeRange(HouseholdsByAgeRange householdsByAgeRange) {
		this.householdsByAgeRange = householdsByAgeRange;
	}

	public HouseholdEarners getHouseholdEarners() {
		return householdEarners;
	}

	public void setHouseholdEarners(HouseholdEarners householdEarners) {
		this.householdEarners = householdEarners;
	}

	public TotalIncome getTotalIncome() {
		return totalIncome;
	}

	public void setTotalIncome(TotalIncome totalIncome) {
		this.totalIncome = totalIncome;
	}

	public CensusYear getCensusYear() {
		return censusYear;
	}

	public void setCensusYear(CensusYear censusYear) {
		this.censusYear = censusYear;
	}

	public int getNumberReported() {
		return numberReported;
	}

	public void setNumberReported(int numberReported) {
		this.numberReported = numberReported;
	} 
}
