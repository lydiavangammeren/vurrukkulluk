import React from 'react';
import Recipe from './Recipe';

const HomeContent = (props) => {

  return (
    <div className='HomeContent'>
      <Recipe title="Eggs & Veggies" image="EggsAndVeggies.jpg" />
      <Recipe title="Vegan Burger" image="VeganBurger.jpg" />
      <Recipe title="Sushi Rolls" image="SushiRolls.jpg" />
      <Recipe title="Pizza Green" image="PizzaGreen.jpg" />
    </div>
  )
}

export default HomeContent
