import React, {useEffect, useState} from 'react'
import useAddRecipeContext from '../../hooks/useAddRecipeContext'
import { SearchBar, SearchResultsList } from './SearchIngredients';
import { useDatabase } from '../../hooks';
import Ingredient from './Ingredient';
import { search } from './SearchIngredients/search';

const IngredientsForm = () => {

  const [ articles, articlesLoaded ] = useDatabase('/articles');
  const [ articleunits, articleunitsLoaded] = useDatabase('/articleunits');
  const [ units, unitsLoaded ] = useDatabase('/units');

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
    
    filtered.forEach(article => {
      article.articleunits = articleunits.filter((articleunit) => {
        return articleunit.article.id === article.id;
      })
    });

    console.log(filtered)
    return filtered;
  }
  const searchResults = filter();


  // const searchResults = search(searchValue, articles);

  const findObjectById = (id) => {
    if(articleunitsLoaded){
      // console.log('Articles: ' +  JSON.stringify(articles))
      const foundObject = articleunits.find((articleunit) => articleunit.id === parseInt(id));
      return foundObject ? foundObject : {name:'Object not found'};
    }
  }

  

  return (
    <div>
      <SearchBar searchValue={searchValue} setSearchValue={setSearchValue}/>
      {searchResults && searchValue.length > 0 &&
      <SearchResultsList results={searchResults} setSearchValue={setSearchValue}/>}

      <div className='ingredient_list'>
        <h3>Toegevoegde ingrediënten</h3>
        {data.ingredients && articlesLoaded &&
        data.ingredients.map((ingredient) => {
          const articleunit = findObjectById(ingredient.articleunitId)

          return (
            <Ingredient 
              articleunit={articleunit}
              ingredient={ingredient}
              key={ingredient.articleunitId}
            />
          )
        })}
      </div>
    </div>
  )
}

export default IngredientsForm
