import React, {useState} from 'react';
import Recipes from './Recipes';
import Pagination from './Pagination';
import { useAppContext } from '../../contexts';

const Home = () => {

  const [currentPage, setCurrentPage] = useState(1);
  const recipesPerPage = 4;
  
  const {recipes, searchValue} = useAppContext();

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
