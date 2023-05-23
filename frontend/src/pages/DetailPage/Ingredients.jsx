import React from "react";
import Ingredient from "./Ingredient";

const Ingredients = ({ingredients}) => {

  return (
    <div className="Ingredients">
      {ingredients && ingredients.map((ingredient, index) => (
          <Ingredient
            image={ingredient.image}
            title={ingredient.title}
            description={ingredient.desc}
            quantity={ingredient.quantity}
          />
      ))}
    </div>
  );
};

export default Ingredients;
