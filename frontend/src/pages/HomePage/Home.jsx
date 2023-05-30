import React, {useState, useEffect} from 'react';
import Recipe from './Recipe';

import api from "../../lib/recipeAPI";
import Recipes from './Recipes';
import Pagination from './Pagination';
import { useRecipes, useRecipesUpdate } from '../../contexts/RecipesContext';

// const Home = ({setImages, recipes, setRecipes}) => {
  const Home = ({setImages}) => {

  const [currentPage, setCurrentPage] = useState(1);
  const recipesPerPage = 4;
  
  const recipes = useRecipes();
  const setRecipes = useRecipesUpdate();


  useEffect(() => {
    const getData = async () => {
      try{
        const response = await api.get('/recipes');
        setRecipes(response.data);

        // Set images for carousel
        // Verzin iets om 5 'random' images te krijgen -> !!
        let items = [];
        for(var i = 0; i < response.data.length; i++){
          if(i > 4) break;
          items.push({src:response.data[i].image, alt:response.data[i].image})
        }
        setImages(items);

      } catch(err){
        if(err.response){
          //Not in the 200 response range
          console.log(err.response.data);
          console.log(err.response.status);
          console.log(err.response.headers);
        }else{
          console.log(`Error: ${err.message}`)
        }
      }
    }
    getData();
  }, [setImages])

  const lastRecipeIndex = currentPage * recipesPerPage;
  const firstRecipeIndex = lastRecipeIndex - recipesPerPage;
  const currentRecipes = recipes.slice(firstRecipeIndex, lastRecipeIndex);

  return (
    <div className='Home'>
      <div className='home-recipes'>
        <Recipes recipes={currentRecipes} />
      </div>
      <Pagination totalPosts={recipes.length}
                  postsPerPage={recipesPerPage}
                  setCurrentPage={setCurrentPage}
                  currentPage={currentPage} />
    </div>
  )
}

export default Home
