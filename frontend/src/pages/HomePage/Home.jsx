import React from 'react';
import Recipe from './Recipe';


const Home = () => {
  const recipes = [
    {
      id:1,
      title: "Eggs & Veggies",
      image: "EggsAndVeggies.jpg",
      kitchen: "Nederlands",
      type: "Vegatarisch"
    },
    {
      id:2,
      title: "Vegan Burger",
      image: "VeganBurger.jpg",
      kitchen: "Amerikaans",
      type: "Vegan"
    },
    {
      id:3,
      title: "Sushi Rolls",
      image: "SushiRolls.jpg",
      kitchen: "Japans",
      type: "Vis"
    },
    {
      id:4,
      title: "Pizza Green",
      image: "PizzaGreen.jpg",
      kitchen: "Italiaans",
      type: "Vlees"
    }];

  return (
    <div className='Home'>
      {recipes.map(recipe => (
        <Recipe recipe={recipe}/>
      ))}
    </div>
  )
}

export default Home
