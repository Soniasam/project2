package com.codekages.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.codekages.dto.AddListOfRecipeDTO;
import com.codekages.dto.IngredientDTO;
import com.codekages.dto.ListOfRecipeDTO;
import com.codekages.model.Ingredient;
import com.codekages.model.ListOfRecipe;
import com.codekages.model.Recipe;
import com.codekages.model.RecipeList;
import com.codekages.model.User;
@Repository
public class ListOfRecipeDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	public ListOfRecipe addListOfRecipe(AddListOfRecipeDTO addListOfRecipeDTO, User user) {
		Session session = sessionFactory.getCurrentSession();

		ListOfRecipe newListOfRecipe = new ListOfRecipe(addListOfRecipeDTO.getListOfRecipe(), user);

		session.persist(newListOfRecipe);
		return newListOfRecipe;
	}

	@Transactional
	public List<ListOfRecipe> getAllListOfRecipe() {
		Session session = sessionFactory.getCurrentSession();

		List<ListOfRecipe> listofrecipe = session.createQuery("FROM ListOfRecipe").getResultList();
		return listofrecipe;

	}
	
	@Transactional
	public RecipeList editListOfRecipe(ListOfRecipe list, Recipe recipe) {
		Session session = sessionFactory.getCurrentSession();

		RecipeList listToAdd = new RecipeList(list, recipe);
		session.saveOrUpdate(listToAdd);
		
		return listToAdd;
	}
	@Transactional 

	public ListOfRecipe getListOfRecipeById(int id) { 

	Session session = sessionFactory.getCurrentSession(); 

	ListOfRecipe listofrecipe = session.get(ListOfRecipe.class, id); 

	return listofrecipe; 

	}
}
