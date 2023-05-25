import React from "react";
import AddRecipeDetails from "./AddRecipeDetails";
import AddRecipeIngredients from "./AddRecipeIngredients";
import AddRecipePreperation from "./AddRecipePreperation";

const AddRecipePage = () => {
  return (
    <div>
      <AddRecipeDetails />

      <AddRecipeIngredients />

      <AddRecipePreperation />
    </div>
  );
};

export default AddRecipePage;
