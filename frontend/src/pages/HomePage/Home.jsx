import React from 'react';
import Recipe from './Recipe';

import { useState, useEffect } from 'react';

// import useSWR from "swr";
// import {getData} from "../../lib/API";


const Home = () => {

  // const {isLoading, error, data, mutate } = useSWR('/recipes', getData, {
  //   onSuccess: (data) => data.sort((a, b) => a.id - b.id),
  // })

  // const renderContent = () => {
  //   if (isLoading) return <h1>Loading...</h1>
  //   if (error) return <h1>Error</h1>

  //   return (
  //     <>
  //       {data &&
  //         data.map((recipe, index) => {
  //           console.log(recipe.id + " " + index);
  //           console.log(recipe.image + " " + index);
  //           console.log(recipe.title + " " + index);
  //           console.log(recipe.description + " " + index);
  //         })}
  //     </>
  //   )
  // }

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
