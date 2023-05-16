import React from 'react';
import Recipe from './Recipe';

import useSWR from "swr";
import {getData} from "../../lib/API";


const Home = () => {

  const {isLoading, error, data, mutate } = useSWR('/recipes', getData, {
    onSuccess: (data) => data.sort((a, b) => a.id - b.id),
  })

  const renderContent = () => {
    if (isLoading) return <h1>Loading...</h1>
    if (error) return <h1>Error</h1>

    return (
      <>
        {data &&
          data.map((recipe, index) => {
            console.log(recipe.id + " " + index);
            console.log(recipe.image + " " + index);
            console.log(recipe.title + " " + index);
            console.log(recipe.description + " " + index);
          })}
      </>
    )
  }

 

  return (
    <div className='Home'>
      {data && data.map(recipe => (
        <Recipe id={recipe.id}
        image={recipe.image}
        title={recipe.title}
        desc={recipe.description}/>
      ))}
      {/* {renderContent()} */}
    </div>
  )
}

export default Home
