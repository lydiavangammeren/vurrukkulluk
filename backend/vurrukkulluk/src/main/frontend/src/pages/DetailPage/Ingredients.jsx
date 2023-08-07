import React from "react";
import Ingredient from "./Ingredient";

const Ingredients = ({ingredients, persons, defPersons}) => {

  return (
    <div className="Ingredients">
      {ingredients && ingredients.map((ingredient, index) => (
          <Ingredient ingredient={ingredient} key={index} persons={persons} defPersons={defPersons} />
      ))}
    </div>
  );
};

export default Ingredients;
