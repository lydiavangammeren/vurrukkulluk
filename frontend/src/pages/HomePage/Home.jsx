import React from 'react';
import Recipe from './Recipe';

const Home = ({recipes}) => {

  return (
    <div className='Home'>
      {recipes.map((recipe, index) => {
        return (
          <Recipe recipe={recipe} />
        )
      })}
    </div>
  )
}

export default Home
