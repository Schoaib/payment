package com.retail.payment.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.retail.payment.dto.BillReponseTO;
import com.retail.payment.dto.BillRequestTO;
import com.retail.payment.service.TransactionService;

import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;
import mockit.integration.junit4.JMockit;

import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * The Class BillControllerTest.
 */
@RunWith(JMockit.class)
public class BillControllerTest {

	/** The bill controller. */
	@Tested
	private BillController billController;

	/** The transaction service. */
	@Injectable
	private TransactionService transactionService;

	/** The mock mvc. */
	private MockMvc mockMvc;

	/**
	 * Initialize mock mvc.
	 */
	private void initializeMockMvc() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(billController).build();
	}

	/** The bill request. */
	BillRequestTO billRequest;

	/**
	 * Sets the up.
	 *
	 * @throws Exception the exception
	 */
	@Before
	public void setUp() throws Exception {
		int[] itemArray = { 1, 2, 3 };
		billRequest = new BillRequestTO();
		billRequest.setCustomerId(1);
		billRequest.setItems(itemArray);

	}

	/**
	 * Test preview.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testPreview() throws Exception {

		BillReponseTO billReponseTO = new BillReponseTO();

		initializeMockMvc();
		new Expectations() {
			{
				transactionService.previewBill(billRequest);
				returns(billReponseTO);
			}
		};

		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = mapper.writeValueAsString(billRequest);
		mockMvc.perform(post("/bill/preview").contentType(MediaType.APPLICATION_JSON).content(jsonInString))
				.andExpect(status().isOk());

	}

}
