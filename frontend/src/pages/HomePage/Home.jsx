import React, {useState, useEffect} from 'react';
// import Recipe from './Recipe';

import api from "../../lib/recipeAPI";
import Recipes from './Recipes';
import Pagination from './Pagination';
// import { useRecipes } from '../../contexts/RecipesContext';
import { useAppContext } from '../../contexts';
// import { useHomePage } from "../../hooks/useHomePage";

const Home = ({}) => {

  const [currentPage, setCurrentPage] = useState(1);
  const recipesPerPage = 4;
  
  const {recipes, searchValue} = useAppContext();
  // const setRecipes = useRecipesUpdate();

  console.log(`Recipes from Home: `);
  console.log(recipes);

  const filter = () => {
    if(!searchValue) return recipes;
    
    return recipes.filter((recipe)=> {
      return (
        searchValue &&
        recipe &&
        recipe.title && (
          recipe.title.toLowerCase().includes(searchValue) ||
          recipe.kitchenRegion.name.toLowerCase().includes(searchValue) ||
          recipe.kitchenType.name.toLowerCase().includes(searchValue) ||
          recipe.categories.includes(searchValue) || //Does not work !!!
          recipe.description.toLowerCase().includes(searchValue)
        )
      )
    });
  }

  const content = filter();
  


  // useEffect(() => {
  //   const getData = async () => {
  //     try{
  //       const response = await api.get('recipes');
  //       setRecipes(response.data);

  //       // Set images for carousel
  //       // Verzin iets om 5 'random' images te krijgen -> !!
  //       let items = [];
  //       for(var i = 0; i < response.data.length; i++){
  //         if(i > 4) break;
  //         items.push({src:response.data[i].image, alt:response.data[i].image})
  //       }
  //       setImages(items);

  //     } catch(err){
  //       if(err.response){
  //         //Not in the 200 response range
  //         console.log(err.response.data);
  //         console.log(err.response.status);
  //         console.log(err.response.headers);
  //       }else{
  //         console.log(`Error: ${err.message}`)
  //       }
  //     }
  //   }
  //   getData();
  // }, [setImages])

  const lastRecipeIndex = currentPage * recipesPerPage;
  const firstRecipeIndex = lastRecipeIndex - recipesPerPage;
  const currentRecipes = content.slice(firstRecipeIndex, lastRecipeIndex);
  // console.log('recipes on First Page: ');
  // console.log(currentRecipes);
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
