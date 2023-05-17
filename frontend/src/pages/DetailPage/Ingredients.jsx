import React, { useState, useEffect } from "react";
// import { useParams } from "react-router-dom";
import Ingredient from "./Ingredient";

const Ingredients = () => {
  // const { id } = useParams();
  const [ingredients, setIngredients] = useState([]);

  const fetchIngredients = () =>{
    fetch(`http://localhost:3004/ingredients`).then(response => response.json()).then((json) => setIngredients(json));
  }

  useEffect(() => {
    fetchIngredients();
  }, [])

  return (
    <div className="Ingredients">
      {ingredients.map((ingredient) => (
        <>
          <Ingredient
            image={ingredient.image}
            title={ingredient.title}
            desc={ingredient.desc}
            quantity={ingredient.quantity}
          />
        </>
      ))}
    </div>
  );
};

export default Ingredients;
