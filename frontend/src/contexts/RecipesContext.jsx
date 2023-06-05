import React, { useContext, useState } from 'react';

const RecipesContext = React.createContext();
// const RecipesUpdateContext = React.createContext();

export function useRecipes() {
  return useContext(RecipesContext);
}

// export function useRecipesUpdate() {
//   return useContext(RecipesUpdateContext);
// }

export function RecipesProvider({ children, data }) {
  const [recipes, setRecipes ] = useState(data);
  // console.log('Data from RecipesProvider: ');
  // console.log(recipes);

  return(
    <RecipesContext.Provider value={{recipes, setRecipes}} >
      {/* <RecipesUpdateContext.Provider value={setRecipes} > */}
        { children }
      {/* </RecipesUpdateContext.Provider> */}
    </RecipesContext.Provider>
  )
}