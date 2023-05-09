import React from 'react';
import Recipe from './Recipe';

const HomeContent = (props) => {

  return (
    <div className='HomeContent'>
      <Recipe title="Eggs & Veggies" image="EggsAndVeggies" />
      <Recipe title="Vegan Burger" image="VeganBurger" />
      <Recipe title="Sushi Rolls" image="SushiRolls" />
      <Recipe title="Pizza Green" image="PizzaGreen" />
    </div>
  )
}

export default HomeContent
