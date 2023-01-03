Feature: Validating Place API's

Scenario Outline: Verify place added successfully
Given User has add place payload with "<name>" "<language>" "<address>"
When User calls "AddPlaceAPI" with Post request
Then User verify that the API call is success with status code 200
And "status" in response body is "OK"
And "scope" in response body is "APP"

Examples:
       |name	   |language |address                      | 
       |Villa No 11|French-IN|South Yamuna, Gachi Bawli, CO|
       |ABC        |Hindi    |NaalaaSupara                 |