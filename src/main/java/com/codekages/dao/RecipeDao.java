package com.codekages.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.codekages.dto.AddRecipeDTO;
import com.codekages.model.Recipe;
import com.codekages.model.User;

@Repository
public class RecipeDao {

	@Autowired
	public SessionFactory sessionFactory;

	@Transactional
	public Recipe addRecipe(AddRecipeDTO addRecipeDto, User user) {
		Session session = sessionFactory.getCurrentSession();

		Recipe newRecipe = new Recipe(addRecipeDto.getName(),addRecipeDto.getDescription(), user);

		session.persist(newRecipe);

		return newRecipe;
	}


	@Transactional
	public List<Recipe> getAllRecipes() {
		Session session = sessionFactory.getCurrentSession();
		
		List<Recipe> recipes = session.createQuery("FROM Recipe r").getResultList();
		
		return recipes;
	} 

	@Transactional 

	public Recipe getRecipeById(int id) { 

	Session session = sessionFactory.getCurrentSession(); 

	Recipe recipe = session.get(Recipe.class, id); 

	return recipe; 

	} 
}
