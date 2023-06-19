import React, {useEffect, useState} from 'react'
import useAddRecipeContext from '../../hooks/useAddRecipeContext'
import { SearchBar, SearchResultsList } from './SearchIngredients';
import { useDatabase } from '../../hooks';
import Ingredient from './Ingredient';

const IngredientsForm = () => {

  const [ articles, articlesLoaded ] = useDatabase('/articles');

  const { data, handleChange, updateIngredientQuantity } = useAddRecipeContext();                                                                                                 
  const [searchValue, setSearchValue] = useState('');
  
  const filter = () => {
    if(!searchValue || !articlesLoaded) return [];
    const filtered = articles.filter((article)=> {
      return (
        searchValue &&
        articlesLoaded && (
          article.name.toLowerCase().includes(searchValue)
        )
      )
    });
    return filtered;
  }
  const searchResults = filter();

  const findObjectById = (id) => {
    if(articlesLoaded){
      // console.log('Articles: ' +  JSON.stringify(articles))
      const foundObject = articles.find((object) => object.id === parseInt(id));
      return foundObject ? foundObject : {name:'Object not found'};
    }
  }

  

  return (
    <div>
      <SearchBar searchValue={searchValue} setSearchValue={setSearchValue}/>
      {searchResults && searchResults.length > 0 && 
      <SearchResultsList results={searchResults} setSearchValue={setSearchValue}/>}

      <div className='ingredient_list'>
        <h3>Toegevoegde ingrediënten</h3>
        {data.ingredients && articlesLoaded &&
        data.ingredients.map((ingredient) => {
          const article = findObjectById(ingredient.articleId)
          return (
            <Ingredient 
              article={article}
              ingredient={ingredient}
            />
          )
        })}
      </div>
    </div>
  )
}

export default IngredientsForm
