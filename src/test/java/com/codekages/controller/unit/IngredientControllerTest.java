package com.codekages.controller.unit;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;

import com.codekages.controller.IngredientController;
import com.codekages.service.IngredientService;

class IngredientControllerTest {

	private MockMvc mockMvc;

	@Mock
	private IngredientService ingredientService;

	@InjectMocks
	private IngredientController ingredientController;

	@BeforeAll
	void setUpBeforeClass() throws Exception {
//		IngredientService mockIngredientService = mock(IngredientService.class);
//		ingredientController = new IngredientController(mockIngredientService);

	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() {
		fail("Not yet implemented");
	}

}
