import React from "react";
import Ingredient from "./Ingredient";
import "../css/Ingredients.css";

const Ingredients = () => {
  const ingredients = [
    {
      id: "1",
      image: "hamb.jpg",
      title: "Vegan Burger Bun",
      desc: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque eu metus sem. Sed lobortis tempor arcu. Nulla id nulla in nibh dictum feugiat. Donec sed accumsan est, at accumsan velit.",
      quantity: "1 Broodje",
    },
    {
      id: "2",
      image: "VeganBurgerI.jpg",
      title: "Vegan Burger",
      desc: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque eu metus sem. Sed lobortis tempor arcu. Nulla id nulla in nibh dictum feugiat. Donec sed accumsan est, at accumsan velit.",
      quantity: "320 gram",
    },
    {
      id: "3",
      image: "VeganSauce.jpg",
      title: "Vegan Burger Sauce",
      desc: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque eu metus sem. Sed lobortis tempor arcu. Nulla id nulla in nibh dictum feugiat. Donec sed accumsan est, at accumsan velit.",
      quantity: "30 ml",
    },
    {
      id: "4",
      image: "avocado.jpg",
      title: "Avocado",
      desc: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque eu metus sem. Sed lobortis tempor arcu. Nulla id nulla in nibh dictum feugiat. Donec sed accumsan est, at accumsan velit.",
      quantity: "1 stuks",
    },
  ];
  return (
    <div className="Ingredients">
      {ingredients.map((ingredient) => (
        <>
          <Ingredient
            image={ingredient.image}
            title={ingredient.title}
            desc={ingredient.desc}
            quantity={ingredient.quantity}
            image={ingredient.image}
          />
        </>
      ))}
    </div>
  );
};

export default Ingredients;
