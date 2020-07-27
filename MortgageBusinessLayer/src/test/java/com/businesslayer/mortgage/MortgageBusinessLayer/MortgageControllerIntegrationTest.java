package com.businesslayer.mortgage.MortgageBusinessLayer;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MortgageBusinessLayerApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MortgageControllerIntegrationTest {

	private int port = 8091;

	TestRestTemplate restTemplate = new TestRestTemplate();

	HttpHeaders headers = new HttpHeaders();

	/*
	 * Test for getMortgages service.
	 */
	@Test
	public void getMortgagesServiceTest() throws JSONException {

		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("mortgage/getMortgages"),
				HttpMethod.GET, entity, String.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody()); // more validation can be added by parsing response.
	}

	/*
	 * Test for getAllMortgagesSortedBy service.
	 */
	@Test
	public void getAllMortgagesSortedByCreatedDateTest() throws JSONException {

		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("mortgage/getAllMortgagesSortedBy/createdDate"), HttpMethod.GET, entity,
				String.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody()); // more validation can be added by parsing response.
	}

	/*
	 * Test for getAllMortgagesSortedBy service.
	 */
	@Test
	public void getAllMortgagesSortedByOfferDateTest() throws JSONException {

		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("mortgage/getAllMortgagesSortedBy/offerDate"), HttpMethod.GET, entity, String.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody()); // more validation can be added by parsing response.
	}

	/*
	 * Test for add Mortgage
	 */
	@Test
	public void addMortgage_PositveTest() {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		String requestJson = "{\"mortgageId\":\"m75\",\"version\":1,\"offerId\":\"Ol-1\",\"productId\":\"B1\",\"offerDate\":\"27/02/2022\",\"createdDate\":\"10/05/2020\",\"expired\":\"N\"}";
		HttpEntity<String> entity = new HttpEntity<String>(requestJson, headers);
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("mortgage/addMortgage"),
				HttpMethod.POST, entity, String.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody()); // more validation can be added by parsing response.
	}

	/*
	 * Test for version validation.
	 */
	@Test
	public void addMortgage_NegativeTest_VersionValidation() {

		// first add valid mortgage
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		String requestJson = "{\"mortgageId\":\"m76\",\"version\":20,\"offerId\":\"Ol-1\",\"productId\":\"B1\",\"offerDate\":\"27/02/2022\",\"createdDate\":\"10/05/2020\",\"expired\":\"N\"}";
		HttpEntity<String> entity = new HttpEntity<String>(requestJson, headers);
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("mortgage/addMortgage"),
				HttpMethod.POST, entity, String.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody()); // more validation can be added by parsing response.

		// Now try to add lower version for same Mortgage. It should fail.
		requestJson = "{\"mortgageId\":\"m76\",\"version\":2,\"offerId\":\"Ol-1\",\"productId\":\"B1\",\"offerDate\":\"27/02/2022\",\"createdDate\":\"10/05/2020\",\"expired\":\"N\"}";
		entity = new HttpEntity<String>(requestJson, headers);
		response = restTemplate.exchange(createURLWithPort("mortgage/addMortgage"), HttpMethod.POST, entity,
				String.class);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}

	/*
	 * Test for attribute validation.
	 */
	@Test
	public void addMortgage_NegativeTest_AttributeValidation() {

		// set invalid expiry flag and try to add mortgage. It should fails
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		String requestJson = "{\"mortgageId\":\"m77\",\"version\":20,\"offerId\":\"Ol-1\",\"productId\":\"B1\",\"offerDate\":\"27/02/2022\",\"createdDate\":\"10/05/2020\",\"expired\":\"INVALID\"}";
		HttpEntity<String> entity = new HttpEntity<String>(requestJson, headers);
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("mortgage/addMortgage"),
				HttpMethod.POST, entity, String.class);

		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}

	/*
	 * Test for offerDate validation
	 */
	@Test
	public void addMortgage_NegativeTest_OfferDateValidation() {

		// Try to add mortgage with past offer data, it should fail.
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		String requestJson = "{\"mortgageId\":\"m78\",\"version\":20,\"offerId\":\"Ol-1\",\"productId\":\"B1\",\"offerDate\":\"27/02/2020\",\"createdDate\":\"10/05/2020\",\"expired\":\"INVALID\"}";
		HttpEntity<String> entity = new HttpEntity<String>(requestJson, headers);
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("mortgage/addMortgage"),
				HttpMethod.POST, entity, String.class);

		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}
	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}
}