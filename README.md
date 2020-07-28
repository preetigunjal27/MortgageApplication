# MortgageApplication
There are two applications in repository -

	1. MortgageBusinessLayer
	2. MortgageDataLayer

These are Gradle projects.

	Java version 1.8
	Sprinb boot version 2.3.2

	Steps to run these applications : 
		1. Import above two projects in eclipse as gradle project
		2. Refresh Gradle projects
		3. Run as spring boot application.

	Business layes will start runnig at URL http://localhost:8091/
	Data layer will start running at URL http://localhost:8088/

	For Testing steps and input data, check ReadMe_Mortgages_Application_Test.txt.


	Design -MortgageController.java is controller for MortgageBusinessLayer. It conatins exposed rest APIs
		1. getMortgages - Returns all mortages stored in storage.
		2. getAllMortgagesSortedBy/{columnName} - Returns all mortgages sorted by created date or offer date (Based on input param)
		3. addMortgage - Adds input mortgages in data Storage

	Mortgage.class POJO class.
	MortgageValidations.java is validation class which validate input mortgage for below criteria befor adding it in storage - 
		1. All attributes are populated. - Date must be in format dd/MM/YYYY. Expiry flag should  be either Y or N.
		2.During transmission if the lower version is received, it rejects the mortgage application and throw an exception. If the version is same it overrides the existing record.		
		3. Offer date - If offer date is considered as valid only if it is farther than current date + 6 months. 
		4. Store should automatically update the expire flag if in a store the mortgage application crosses the offer date.

	If all these validations are passed, then REST APIs from Data layer are called.
	If validation failed, then appropriate errorCode and message will be returned.

	MortgageDataController.java is controller for second application MortgageDataLayer.
	It exposes below services - 
		1. getMortgagesData - returns all mortgages from storage
		2. getAllMortgageSortedBy/{columnName} - retuns sorted mortgages 
		3. addMortgageData - stores mortgages in storage.
		4. getMaxVersionById/{mId} - returns maximum version of input mortgage id.

	MortgageDAO.java is storage class. It has dynamic array which holds all the mortgages.
	Data layer also starts - ScheduledTasks which runs every day to check any mortgages is expired or not?
	If expired then it is being set to Y. 
	Expiration is decided by checking current date and offer date . If offer date is less than current date then mortgage is considedred as expired.
	
	Steps to test Schedular:
		1. Update schedularTasks.java and set fixRate=300000.
		2. Build and relaunch application.
		3. Add some Mortgages which will have offerDate in past. (Use data layer service directly from Postman, this will skip validation for OfferDate).
		4. Wait for 30sec and run getMortgages service.
	Expected Output : Mortgages with past OfferDate should have expired flag 'N'.

	Test 
	MortgageControllerIntegrationTest contains integration tests for all uses cases.
	Unit tests are also avaialable.
	Total code coverage is around ~90%


