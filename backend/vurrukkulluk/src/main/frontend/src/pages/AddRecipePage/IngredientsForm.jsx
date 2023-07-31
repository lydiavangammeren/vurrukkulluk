import React, {useEffect, useState} from 'react'
import useAddRecipeContext from '../../hooks/useAddRecipeContext'
import { SearchBar, SearchResultsList } from './SearchIngredients';
import { useDatabase } from '../../hooks';
import Ingredient from './Ingredient';
import { search } from './SearchIngredients/search';

const IngredientsForm = () => {

  const [ articles, articlesLoaded ] = useDatabase('/articles');
  const [ articleunits, articleunitsLoaded] = useDatabase('/articleunits');
  // const [ units, unitsLoaded ] = useDatabase('/units');

  const { data, handleChange, updateIngredientQuantity } = useAddRecipeContext();                                                                                                 
  const [searchValue, setSearchValue] = useState('');

  const searchResults = search(searchValue, articles?? [], articleunits?? []);

  const findObjectById = (id) => {
    if(articleunitsLoaded){
      // console.log('Articles: ' +  JSON.stringify(articles))
      const foundObject = articleunits.find((articleunit) => articleunit.id == parseInt(id));
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
        {data.ingredients && articlesLoaded && articleunitsLoaded &&
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
