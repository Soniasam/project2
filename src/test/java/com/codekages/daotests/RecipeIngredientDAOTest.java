package com.codekages.daotests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.codekages.dao.RecipeDao;
import com.codekages.dao.RecipeIngredientDao;
import com.codekages.dao.UserDAO;
import com.codekages.dto.AddIngredientDTO;
import com.codekages.dto.AddRecipeDTO;
import com.codekages.dto.AddRecipeIngredientDTO;
import com.codekages.dto.AddUserDTO;
import com.codekages.model.Ingredient;
import com.codekages.model.ListOfRecipe;
import com.codekages.model.Recipe;
import com.codekages.model.RecipeIngredient;
import com.codekages.model.User;
import com.codekages.dao.IngredientDAO;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:applicationContext.xml")
@WebAppConfiguration
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestPropertySource("classpath:springorm-test.properties")
public class RecipeIngredientDAOTest {

	@Autowired
	private RecipeIngredientDao recipeIngredientDao;
	@Autowired
	private UserDAO userdao;
	@Autowired
	private RecipeDao recipedao;
	@Autowired
	private IngredientDAO ingredientdao;
	@Autowired
	private SessionFactory sessionfactory;

	@Test
	@Transactional
	@Order(0)
	@Commit
	void testAddRecipeIngredient_hasAutoGeneratedId() {
		AddRecipeIngredientDTO dto = new AddRecipeIngredientDTO();
		dto.setQuantity(1);

		AddUserDTO userDto = new AddUserDTO();
		userDto.setUsername("user123");
		userDto.setPassword("12345");
		userDto.setFirstName("John");
		userDto.setLastName("Deo");
		userdao.addUser(userDto);
		User user = userdao.getUserByUsernameAndPassword("user123", "12345");
		user.setUserRole(1);

		AddRecipeDTO recipedto = new AddRecipeDTO();
		recipedto.setName("Chicken Tikka Masala");
		recipedto.setDescription("It is a dish consisting of roasted marinated chicken chunks in spiced curry sauce.");
		recipedao.addRecipe(recipedto, user);
		Recipe recipe = recipedao.getRecipeById(1);

		AddIngredientDTO ingredientdto = new AddIngredientDTO();
		ingredientdto.setName("Chicken");
		ingredientdto.setCost(15.00);
		ingredientdao.addIngredient(ingredientdto);
		Ingredient ingredient = ingredientdao.getIngredientById(1);

		RecipeIngredient recipeIngredient = recipeIngredientDao.addRecipeIngredient(dto, recipe, ingredient);

		assertEquals(1, recipeIngredient.getRiID());
	}

	@Test
	@Transactional
	@Order(1)
	void testGetRecipeIngredientById_Success() {
		RecipeIngredient actual = recipeIngredientDao.getRecipeIngredientById(1);
		User user = new User();
		user.setFirstName("John");
		user.setLastName("Deo");
		user.setId(1);
		user.setUsername("user123");
		user.setPassword("12345");
		user.setUserRole(1);

		Recipe recipe = new Recipe();
		recipe.setId(1);
		recipe.setRecipeName("Chicken Tikka Masala");
		recipe.setRecipeDescription(
				"It is a dish consisting of roasted marinated chicken chunks in spiced curry sauce.");
		recipe.setUser(user);

		Ingredient ingredient = new Ingredient();
		ingredient.setId(1);
		ingredient.setName("Chicken");
		ingredient.setCost(15.00);

		RecipeIngredient expected = new RecipeIngredient(1, recipe, ingredient);
		expected.setRiID(1);

		assertEquals(expected, actual);
	}

	@Test
	@Transactional
	@Order(2)
	void testGetRecipeIngredientById_doesNotExsit() {
		RecipeIngredient actual = recipeIngredientDao.getRecipeIngredientById(1000);

		RecipeIngredient expected = null;
		assertEquals(expected, actual);
	}
}