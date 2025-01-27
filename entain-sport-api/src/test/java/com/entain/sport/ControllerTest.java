package com.entain.sport;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;


@SpringBootTest
( webEnvironment = SpringBootTest.WebEnvironment.MOCK,
 classes = SportApplication.class)
@AutoConfigureMockMvc
public class ControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@Test
	public void testGetEventById() throws Exception {
		 mvc.perform(get("/api/v01/events/1")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk())
			      .andExpect(content()
			    	      .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			    	      .andExpect((ResultMatcher) jsonPath("$.name", is("Eurobasket 2025")));
	}

	@Test
	public void testApplicationAccess() throws Exception {
		 mvc.perform((RequestBuilder) ((ResultActions) get("/api/v01/hello"))
				 .andExpect(status().isOk()));
	}
}
