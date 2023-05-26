import React from 'react'
import Recipe from './Recipe'
import "./Recipes.css";

const Recipes = ({recipes}) => {
  return (
    <div className='recipes'>
      {recipes.map((recipe, index)=> {
        return (
          <Recipe recipe={recipe} key={index}/>
        )
      })}
    </div>
  )
}

export default Recipes
