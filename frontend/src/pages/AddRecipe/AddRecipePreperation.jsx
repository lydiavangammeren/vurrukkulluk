import React, { useState } from "react";
import "./AddRecipePage";
import "./AddRecipePreperation.css";

const AddRecipePreperation = () => {
  const [Recipe, setRecipe] = useState([{ recipe: "", number: 1 }]);

  const handleRecipeChange = (index, event) => {
    const newRecipe = [...Recipe];
    newRecipe[index].recipe = event.target.value;
    setRecipe(newRecipe);
  };

  const handleNumberChange = (index, event) => {
    const newRecipe = [...Recipe];
    newRecipe[index].number = parseInt(event.target.value);
    setRecipe(newRecipe);
  };

  const addRecipe = () => {
    const newRecipe = [...Recipe];
    const nextNumber = newRecipe[newRecipe.length - 1].number + 1;
    newRecipe.push({ Recipe: "", number: nextNumber });
    setRecipe(newRecipe);
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    console.log(Recipe);
  };

  return (
    <div className="AddPreperation">
      <h1>Voeg bereiding stappen toe</h1>
      <form onSubmit={handleSubmit}>
        {Recipe.map((recipe, index) => (
          <div key={index}>
            <input
              type="text"
              value={recipe.recipe}
              placeholder={`Step ${recipe.number}`}
              onChange={(event) => handleRecipeChange(index, event)}
            />
            <input
              type="number"
              value={Recipe.number}
              min="1"
              onChange={(event) => handleNumberChange(index, event)}
            />
          </div>
        ))}
        <button type="button" onClick={addRecipe}>
          Voeg bereiding stap toe
        </button>
        <br />
        <br />
        <input type="submit" value="Submit" />
      </form>
    </div>
  );
};

export default AddRecipePreperation;
