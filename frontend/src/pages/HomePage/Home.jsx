import React from 'react';
import Recipe from './Recipe';

import { useState, useEffect } from 'react';

// import useSWR from "swr";
// import {getData} from "../../lib/API";


const Home = () => {

  const [recipes, setRecipes] = useState([]);

  const fetchRecipes = () =>{
    fetch('http://localhost:3004/recipes').then(response => response.json()).then((json) => setRecipes(json));
  }

  useEffect(() => {
    console.log('onMount')
    fetchRecipes();
  }, [])

  return (
    <div className='Home'>
      {recipes.map((recipe, index) => {
        return (
          <Recipe id={recipe.id}
                  image={recipe.image}
                  title={recipe.title}
                  desc={recipe.description}/>
        )
      })}
    </div>
  )
}

export default Home
