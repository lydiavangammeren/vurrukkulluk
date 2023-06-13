import React, {useEffect, useState} from 'react'
import useAddRecipeContext from '../../hooks/useAddRecipeContext'
import { SearchBar, SearchResultsList } from './SearchIngredients';
import { useDatabase } from '../../hooks';

const IngredientsForm = () => {

  const [ articles, articlesLoaded ] = useDatabase('/articles');

  const { data, handleChange } = useAddRecipeContext();

  const [searchValue, setSearchValue] = useState('');

  // const searchResults = articles.filter((a)=>{
  //   return (
  //     searchValue && articlesLoaded &&
  //     a.name.toLowerCase().includes(searchValue)
  //   )
  // })

  const filter = () => {
    // console.log('Articles: ' + JSON.stringify(articles))
    // console.log('Loaded: ' + articlesLoaded)
    if(!searchValue || !articlesLoaded) return [];
    // console.log('Articles after Loaded: ' + JSON.stringify(articles))
    return articles.filter((article)=> {
      return (
        searchValue &&
        articlesLoaded && (
          article.name.toLowerCase().includes(searchValue)
        )
      )
    });
  }

  const searchResults = filter();

  return (
    <div>
      <SearchBar searchValue={searchValue} setSearchValue={setSearchValue}/>
      {searchResults && searchResults.length > 0 && 
      <SearchResultsList results={searchResults} setSearchValue={setSearchValue}/>}

      <div className='ingredient_list'>
        {data.ingredients.map((ingredient) => {
          // console.log('Print Category: ' + categoryId)

          return (
            // <li>{findObjectNameById(categoryId)}</li>
            <div className='ingredient_item'>
              <span>Ingredient: {ingredient.articleId}</span>
            </div>
          )
        })}
      </div>
    </div>
  )
}

export default IngredientsForm
