import React from 'react';
import Recipe from './Recipe';

const HomeContent = (props) => {

  return (
    <div className='HomeContent'>
      <Recipe title="Eggs & Veggies" />
      <Recipe title="Vegan Burger" />
      <Recipe title="Sushi Rolls" />
      <Recipe title="Pizza Green" />
    </div>
  )
}

export default HomeContent
