package javaClasses;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class Exercise3 {

    static final String PERSISTENCE_UNIT_NAME = "Exercise3";
    
	public static void main(String[] args) {
		
		//Declare variable for query 
		int var_altCode=3555024;
        int var_censusYear=2016;
        int var_GEO_code=24550;
        int var_level=3;
        String var_new_GEO_Name="Guelph Qubec part";
		String var_HouseHoldType = "Non-census-family households";
		String var_HouseHoldSize = "2 or more persons";
		String var_ByAgeRange = "Total - Households by number of persons aged 0 to 17 years";
		String var_Earning ="1 earner or more";
		String var_TotalIncome="$70,000 to $79,999";
		
        EntityManagerFactory tempEntityManagerFactory = null;
        EntityManager tempEntityManager = null;
        
        try
        {
            tempEntityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
            tempEntityManager = tempEntityManagerFactory.createEntityManager();
            tempEntityManager.getTransaction().begin();
            
            // Get all age objects belonging to the Team specified above 
            String tempJPQLStatement = "SELECT cy, hh, ge, ht, hz, har, hhe, ti "
				            		+ "FROM HouseHold hh "
				            		+ "JOIN hh.censusYear cy "
				            		+ "JOIN hh.geographicArea ge "
				            		+ "JOIN hh.householdType ht "
				            		+ "JOIN hh.householdSize hz "
				            		+ "JOIN hh.householdsByAgeRange har "
				            		+ "JOIN hh.householdEarners hhe "
				            		+ "JOIN hh.totalIncome ti "
				            		+ "WHERE cy.censusYear = :c_year "
				            		+ "AND ge.alternativeCode=:c_altCode "
				            		+ "AND ht.description=:c_householdType "
				            		+ "AND hz.description=:c_HouseHoldSize "
				            		+ "AND har.description=:c_ByAgeRange "
				            		+ "AND hhe.description=:c_Earning "
				            		+ "AND ti.description=:c_TotalIncome";
            
            Query tempJPQLQuery = tempEntityManager.createQuery(tempJPQLStatement)
            		.setParameter("c_year", var_censusYear)
            		.setParameter("c_altCode", var_altCode)
            		.setParameter("c_householdType", var_HouseHoldType)
            		.setParameter("c_HouseHoldSize", var_HouseHoldSize)
            		.setParameter("c_ByAgeRange", var_ByAgeRange)
            		.setParameter("c_Earning", var_Earning)
            		.setParameter("c_TotalIncome", var_TotalIncome);
            
            // Creating a fictitious GeographicArea to insert into the database
            GeographicArea tempGEOToInsert = new GeographicArea();
            tempGEOToInsert.setCode(var_GEO_code);
            tempGEOToInsert.setLevel(var_level);
            tempGEOToInsert.setName(var_new_GEO_Name);
            tempGEOToInsert.setAlternativeCode(var_altCode);
       
            // Persist the Player object to database
            tempEntityManager.persist(tempGEOToInsert);
   
            //Search newly enter data in GeographicArea
            String tempHQLastGeo = "SELECT m FROM GeographicArea m "
            		+ "WHERE m.alternativeCode=:cAltCode";
            Query  tempLastGeo = tempEntityManager.createQuery(tempHQLastGeo)
            		.setParameter("cAltCode", var_altCode);
            
            List <GeographicArea> tempLastGeoCode = tempLastGeo.getResultList();
            Iterator <GeographicArea> tempIter1 = tempLastGeoCode.iterator();
            int temGeoAreaLast=0;
 
            while (tempIter1.hasNext())
            {
            	GeographicArea tempGEOarea = tempIter1.next();
            	temGeoAreaLast=tempGEOarea.getGeographicAreaID();
            }

            // INSERT the household data in age table
            HouseholdType temHY = tempEntityManager.find(HouseholdType.class, 11);
            HouseholdSize temHS = tempEntityManager.find(HouseholdSize.class, 3);
            HouseholdsByAgeRange temHBA = tempEntityManager.find(HouseholdsByAgeRange.class, 7);
            HouseholdEarners temHE = tempEntityManager.find(HouseholdEarners.class, 3);
            TotalIncome temIncome = tempEntityManager.find(TotalIncome.class, 14);
            CensusYear temCY = tempEntityManager.find(CensusYear.class, 1);
            GeographicArea temGA =tempEntityManager.find(GeographicArea.class, temGeoAreaLast);

            HouseHold temHouseHoldToInsert = new HouseHold();
            temHouseHoldToInsert.setHouseholdType(temHY);
            temHouseHoldToInsert.setHouseholdSize(temHS);
            temHouseHoldToInsert.setHouseholdsByAgeRange(temHBA);
            temHouseHoldToInsert.setHouseholdEarners(temHE);
            temHouseHoldToInsert.setTotalIncome(temIncome);
            temHouseHoldToInsert.setCensusYear(temCY);
            temHouseHoldToInsert.setGeographicArea(temGA);
            temHouseHoldToInsert.setNumberReported(1);
            
            // INSERT the house Hold object
            tempEntityManager.persist(temHouseHoldToInsert);
            
            // Setting maximum number of results may be useful in some cases for pagination
            tempJPQLQuery.setMaxResults(10);
            List<Object[]> tempList = tempJPQLQuery.getResultList();
            Iterator <Object[]> tempResultListIterator = tempList.iterator();

            // Produce a simple report showing some data about each player on the retrieved team
            System.out.println("");
            System.out.println("Begin Report.");

            List <String> tempOutputTable = new ArrayList <String>();

            tempOutputTable.add(String.format("%-15s", "Census Year")
                    + String.format("%-20s", "Name")
                    + String.format("%-15s", "Code")
                    + String.format("%-15s", "Level")
                    + String.format("%-15s", "Alt. code")
                    + String.format("%-15s", "Reported"));
            
            while (tempResultListIterator.hasNext())
            {
            	Object[] tempResultSet = tempResultListIterator.next();
            	
            	CensusYear tempCYear = (CensusYear) tempResultSet[0];
            	HouseHold temHouseHold= (HouseHold) tempResultSet[1];
            	GeographicArea temGeographicArea=(GeographicArea) tempResultSet[2];
            	
            	int tempCensusYear = tempCYear.getCensusYear();
            	
            	String tempGEOName = temGeographicArea.getName();
            	int tempGEOCode = temGeographicArea.getCode();
            	int tempGEOLevel = temGeographicArea.getLevel();
            	int tempAltGEOCode = temGeographicArea.getAlternativeCode();
            	
            	int temNumberOfReport= temHouseHold.getNumberReported();
            	
                tempOutputTable.add(String.format("%-15s", tempCensusYear)
                        + String.format("%-20s", tempGEOName)
                        + String.format("%-15s", tempGEOCode)
                        + String.format("%-15s", tempGEOLevel)
                        + String.format("%-15s", tempAltGEOCode)
                        + String.format("%-15s", temNumberOfReport));
            }

            PrintOutput("CensusDB: ", tempOutputTable);
            System.out.println("Report done.");
            tempEntityManager.getTransaction().rollback();
        }
        catch (Exception e)
        {

            if (tempEntityManager != null)
            {
                tempEntityManager.getTransaction().rollback();
            }

            e.printStackTrace();
        }
        finally
        {

            if (tempEntityManager != null)
            {
                tempEntityManager.close();
            }

            if (tempEntityManagerFactory != null)
            {
                tempEntityManagerFactory.close();
            }
        }
	}
    private static void PrintOutput(String tempOutputTableTitle, List <String> tempOutputTable)
    {

        System.out.println("******************************************************************************************");
        System.out.println();
        System.out.println(tempOutputTableTitle);
        System.out.println();

        for (String tempOutputTableElement : tempOutputTable)
        {
            System.out.println(tempOutputTableElement);
        }

        System.out.println();
        System.out.println("******************************************************************************************");

    }
}
