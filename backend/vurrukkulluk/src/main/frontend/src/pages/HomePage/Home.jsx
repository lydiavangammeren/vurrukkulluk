import React, {useState} from 'react';
import Recipes from './Recipes';
import Pagination from './Pagination';
import { useAppContext } from '../../contexts';
import { search } from './search';

const Home = () => {

  const [currentPage, setCurrentPage] = useState(1);
  const recipesPerPage = 4;
  
  const {recipes, searchValue} = useAppContext();

  const content = search(searchValue, recipes);

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
