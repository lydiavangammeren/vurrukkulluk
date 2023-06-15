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
  useEffect(() => {
    console.log('articles changed:')
    console.log(JSON.stringify(articles))
  }, [articles])

  const filter = () => {
    // console.log('Articles: ' + JSON.stringify(articles))
    // console.log('Loaded: ' + articlesLoaded)
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

  // const findObjectNameById = (id) => {
  //   if(articlesLoaded){
  //     console.log('Articles: ' +  JSON.stringify(articles))
  //     const foundObject = articles.find((object) => object.id === parseInt(id));
  //     return foundObject ? foundObject.name : 'Object not found';
  //   }
  // }

  const findObjectById = (id) => {
    if(articlesLoaded){
      console.log('Articles: ' +  JSON.stringify(articles))
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
        {data.ingredients.map((ingredient) => {
          const article = findObjectById(ingredient.articleId)
          return (
            <div className='ingredient_item'>
              <span>Ingredient: {article.name}</span>
              <div>
                <label htmlFor='quantity'>Hoeveelheid:</label>
                <input type='number'
                  value={ingredient.amount}
                  />
                  <span>{article.unit}</span>
              </div>
            </div>
          )
        })}
      </div>
    </div>
  )
}

export default IngredientsForm
