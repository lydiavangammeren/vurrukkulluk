import React, { useContext, useState } from 'react';

const RecipesContext = React.createContext();
const RecipesUpdateContext = React.createContext();

export function useRecipes() {
  return useContext(RecipesContext);
}

export function useRecipesUpdate() {
  return useContext(RecipesUpdateContext);
}

export function RecipesProvider({ children }) {
  const [recipes, setRecipes ] = useState([]);

  return(
    <RecipesContext.Provider value={recipes} >
      <RecipesUpdateContext.Provider value={setRecipes} >
        { children }
      </RecipesUpdateContext.Provider>
    </RecipesContext.Provider>
  )
}