import React from 'react';
import Ingredient from './Ingredient';

const Ingredients = () => {
  const ingredients = [
    {
      id: "1",
      image: "",
      title: "Vegan Burger Bun",
      desc: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque eu metus sem. Sed lobortis tempor arcu. Nulla id nulla in nibh dictum feugiat. Donec sed accumsan est, at accumsan velit.",
      quantity: "1 Broodje"
    },
    {
      id: "2",
      image: "",
      title: "Vegan Burger",
      desc: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque eu metus sem. Sed lobortis tempor arcu. Nulla id nulla in nibh dictum feugiat. Donec sed accumsan est, at accumsan velit.",
      quantity: "320 gram"
    },
    {
      id: "3",
      image: "",
      title: "Vegan Burger Sauce",
      desc: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque eu metus sem. Sed lobortis tempor arcu. Nulla id nulla in nibh dictum feugiat. Donec sed accumsan est, at accumsan velit.",
      quantity: "30 ml"
    },
    {
      id: "4",
      image: "",
      title: "Avocado",
      desc: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque eu metus sem. Sed lobortis tempor arcu. Nulla id nulla in nibh dictum feugiat. Donec sed accumsan est, at accumsan velit.",
      quantity: "1 stuks"
    }
  ]
  return (
    <div className='Ingredients'>
      {ingredients.map(ingredient => {
        <Ingredient />
      })}
    </div>
  )
}

export default Ingredients

