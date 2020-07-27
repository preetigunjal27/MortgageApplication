************************************************************************************************************
*** Business Layer - getAllMortgagesSortedBy***
************************************************************************************************************
At Business layer, Retrieve all Mortgages in sorted manner by OfferDate or CreateDate- REST GET API exposed by Data layer.
	URL:
		http://localhost:8091/mortgage/getAllMortgagesSortedBy/offerDate  OR
		http://localhost:8091/mortgage/getAllMortgagesSortedBy/createDate
	
	Method:
		GET
	
	Data Params:
		None 

	Success Response: 
		Code: 200
		Content:{
				"mortgageId": "M1",
				"version": 1,
				"offerId": "Ol-1",
				"productId": "B1",
				"offerDate": "27-02-2021",
				"createdDate": "28-02-2020",
				"expired": "N"
				}
 
	Error Response:
		Code: 404 NOT FOUND 
		Content: { error : "No message available" }

	Sample Call:
		curl --location --request GET 'http://localhost:8091/mortgage/getMortgage'
*
***********************************************************************************************
***Business Layer - getMortgages***
***********************************************************************************************
At Business layer, Retrieve all Mortgages without Sorting REST GET API exposed by Data layer.
	URL:
		http://localhost:8091/mortgage/getMortgages
	
	Method:
		GET
	
	Data Params:
		None

	Success Response: 
		Code: 200 
		Content:
    {
        "mortgageId": "M98",
        "version": 1,
        "offerId": "Ol-1",
        "productId": "B1",
        "offerDate": "28/02/2021",
        "createdDate": "28/07/2020",
        "expired": "N"
    },
    {
        "mortgageId": "M99",
        "version": 1,
        "offerId": "Ol-1",
        "productId": "B1",
        "offerDate": "28/02/2021",
        "createdDate": "28/07/2020",
        "expired": "N"
    }
]
 
	Error Response:
		Code: 404 NOT FOUND 
		Content: { error : "No message available" }

	Sample Call:
		curl --location --request GET 'http://localhost:8091/mortgage/getMortgage'
*
*************************************************************************************************	
***Business Layer - addMortgage***
*************************************************************************************************
At Business layer, insert a Mortgage -REST POST API exposed by Data layer.
	URL:
		http://localhost:8091/mortgage/addMortgage
	
	Method:
		POST
	
	Data Params:
	    mortgageId=[String],version=[integer],offerId=[String],productId=[String],offerDate=[String],createdDate=[String],expired=[String]
		{
        "mortgageId": "M1",
        "version": 1,
        "offerId": "Ol-1",
        "productId": "B1",
        "offerDate": "28/02/2021",
        "createdDate": "28/07/2020",
        "expired": "N"
    }

	Success Response: 
		Code: 200 
		Content:{
			"mortgageId": "M1",
			"version": 1,
			"offerId": "Ol-1",
			"productId": "B1",
			"offerDate": "28/02/2021",
			"createdDate": "28/07/2020",
			"expired": "N"
        }
 
	Error Response:
	
	Code: 400,
    error": "Bad Request",
    message": "Provide value for all mortgage attributes",
    		
	Sample Call:
		curl --location --request POST 'http://localhost:8091/mortgage/addMortgage' \
		--header 'Content-Type: application/json' \
		--data-raw '{
		"mortgageId": "M12",
		"version": 1,
		"offerId": "Ol-1",
		"productId": "B1",
		"offerDate": "27-02-2021",
		"createdDate": "2020-07-28",
		"expired": "N"
        }'
****************************************************************************************
***Data Layer - getAllMortgageSortedBy***
****************************************************************************************	
Retrieve all Mortgages in sorted manner by OfferDate or CreateDate- REST GET API exposed by Data layer.
	
	URL:
		http://localhost:8088/mortgageData/getAllMortgageSortedBy/offerDate   OR
		http://localhost:8088/mortgageData/getAllMortgageSortedBy/createdDate
	
	Method:
		GET
	
	Data Params:
		None

	Success Response: 
		Code: 200 
		Content:{
				"mortgageId": "M1",
				"version": 1,
				"offerId": "Ol-1",
				"productId": "B1",
				"offerDate": "27-02-2021",
				"createdDate": "28-02-2020",
				"expired": "N"
				}
 
	Error Response:
		Code: 404 NOT FOUND 
		Content: { error : "No message available" }

	Sample Call:
		curl --location --request GET 'http://localhost:8091/mortgage/getMortgage'
*		
****************************************************************************************
***Data Layer - getMortgages***
****************************************************************************************	
Retrieve all Mortgages without sorting- REST GET API exposed by Data layer.
	
	URL:
		http://localhost:8088/mortgageData/getMortgagesData
	
	Method:
		GET
	
	Data Params:
		None

	Success Response: 
		Code: 200 
		Content:{
				"mortgageId": "M1",
				"version": 1,
				"offerId": "Ol-1",
				"productId": "B1",
				"offerDate": "27-02-2021",
				"createdDate": "28-02-2020",
				"expired": "N"
				}
 
	Error Response:
		Code: 404 NOT FOUND 
		Content: { error : "No message available" }

	Sample Call:
		curl --location --request GET 'http://localhost:8091/mortgage/getMortgage'
*
***************************************************************************************	
***Data Layer - addMortgage***
***************************************************************************************
Insert a Mortgage -REST POST API exposed by Data layer.
	URL:
		http://localhost:8088/mortgageData/addMortgageData
	Method:
		POST
	
	Data Params:
		mortgageId=[String],version=[integer],offerId=[String],productId=[String],offerDate=[String],createdDate=[String],expired=[String]
		{
			"mortgageId": "M1",
			"version": 1,
			"offerId": "Ol-1",
			"productId": "B1",
			"offerDate": "27-02-2021",
			"createdDate": "28-02-2020",
			"expired": "N"
		}

	Success Response: 
		Code: 200 
		Content:{
				"mortgageId": "M12",
				"version": 1,
				"offerId": "Ol-1",
				"productId": "B1",
				"offerDate": "27-02-2021",
				"createdDate": "28-02-2020",
				"expired": "N"
				}
 
	Error Response:
		Code: 404 NOT FOUND 
		Content: { error : "No message available" }

	Sample Call:
		curl --location --request POST 'http://localhost:8091/mortgage/addMortgage' \
		--header 'Content-Type: application/json' \
		--data-raw '{
		"mortgageId": "M12",
		"version": 1,
		"offerId": "Ol-1",
		"productId": "B1",
		"offerDate": "27-02-2021",
		"createdDate": "28-02-2020",
		"expired": "N"
        }'
****************************************************************************************
***Data Layer - getMortgageMaxVersionById***
****************************************************************************************	
Retrieve max version for MortgageId- REST GET API exposed by Data layer.
	
	URL:
		http://localhost:8088/mortgageData/getMaxVersionById/{mortgageId}
	
	Method:
		GET
	
	Data Params:
		None

	Success Response: 
		Code: 200 
		Content:{
				"mortgageId": "M98",
				"version": 3,
				"offerId": "Ol-1",
				"productId": "B1",
				"offerDate": "27-02-2021",
				"createdDate": "28-02-2020",
				"expired": "N"
				}
 
	Error Response:
		Code: 404 NOT FOUND 
		Content: { error : "No message available" }

	Sample Call:
		curl --location --request GET 'http://localhost:8091/mortgage/getMortgage'
*		