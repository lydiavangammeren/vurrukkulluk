import React from 'react';
import Recipe from './Recipe';

const Home = ({recipes}) => {

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
