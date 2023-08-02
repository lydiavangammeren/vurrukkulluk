import React, {useEffect, useState} from 'react'
import useAddRecipeContext from '../../hooks/useAddRecipeContext'
import { SearchBar, SearchResultsList } from './SearchIngredients';
import { useDatabase } from '../../hooks';
import Ingredient from './Ingredient';
import { search } from './SearchIngredients/search';
// import api from "../../lib/recipeAPI";

const IngredientsForm = () => {

  const [ articles, articlesLoaded, keepArticleCache ] = useDatabase('/articles');
  // const [ currentArticles, setCurrentArticles] = useState([]);
  // const [searchResults, setSearchResults] = useState([]);
  const [ articleunits, articleunitsLoaded, keepArticleunitsCache] = useDatabase('/articleunits');
  // const [ units, unitsLoaded ] = useDatabase('/units');
  const [newArticles, setNewArticles] = useState(false);
  // const [ , updateState] = useState();
  // const forceUpdate = React.useCallback(() => updateState({}), []);

  const { data, handleChange, updateIngredientQuantity } = useAddRecipeContext();                                                                                                 
  const [searchValue, setSearchValue] = useState('');

  const searchResults = search(searchValue, articles?? [], articleunits?? []);
  // setSearchResults(search(searchValue, currentArticles?? [], articleunits?? []))

  const findObjectById = (id) => {
    if(articleunitsLoaded){
      // console.log('Articles: ' +  JSON.stringify(articles))
      const foundObject = articleunits.find((articleunit) => articleunit.id == parseInt(id));
      return foundObject ? foundObject : {name:'Object not found'};
    }
  }

  useEffect(() => {
    if(!newArticles) {
      // setCurrentArticles(articles);
      return;
    };
    // const getData = async () => {
    //   try{
    //     const response = await api.get('/articles');
    //     console.log('Articles from database:')
    //     console.log(JSON.stringify(response.data))
    //     // setArticles(response.data);
    //     // setCurrentArticles(response.data);
    //     // forceUpdate();
        
    //   } catch(err){
    //     if(err.response){
    //       //Not in the 200 response range
    //       console.log(err.response.data);
    //       console.log(err.response.status);
    //       console.log(err.response.headers);
    //     }else{
    //       console.log(`Error: ${err.message}`)
    //     }
    //   }
    // }
    // getData();

    keepArticleCache(false);
    keepArticleunitsCache(false);
    
    return (setNewArticles(false))
  }, [newArticles])

  return (
    <div>
      <SearchBar searchValue={searchValue} setSearchValue={setSearchValue}/>
      {searchResults && searchValue.length >= 1 &&
      <SearchResultsList results={searchResults} setSearchValue={setSearchValue} newArticles={newArticles} setNew={setNewArticles}/>}

      <h2>Toegevoegde ingrediënten</h2>
      <div className='ingredient_list'>
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
