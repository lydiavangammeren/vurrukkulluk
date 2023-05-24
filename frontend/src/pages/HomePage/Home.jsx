import React, {useState, useEffect} from 'react';
import Recipe from './Recipe';

import api from "../../lib/recipeAPI";

const Home = ({setImages}) => {
  const [recipes, setRecipes] = useState([]);

  useEffect(() => {
    const getData = async () => {
      try{
        const response = await api.get('/recipes');
        setRecipes(response.data);

        // Set images for carousel
        let items = [];
        response.data.map((recipe) => {
          return items.push({src:recipe.image, alt:recipe.image})
        })
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

  return (
    <div className='Home'>
      {recipes.map((recipe) => {
        return (
          <Recipe recipe={recipe} />
        )
      })}
    </div>
  )
}

export default Home
