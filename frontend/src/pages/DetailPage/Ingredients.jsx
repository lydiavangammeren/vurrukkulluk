import React from "react";
import Ingredient from "./Ingredient";

const Ingredients = ({ingredients}) => {

  return (
    <div className="Ingredients">
      {ingredients && ingredients.map((ingredient, index) => (
          <Ingredient ingredient={ingredient} key={index} />
      ))}
    </div>
  );
};

export default Ingredients;
