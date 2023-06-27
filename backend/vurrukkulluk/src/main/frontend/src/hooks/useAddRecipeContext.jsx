import { useContext } from "react";
import AddRecipeContext from "../contexts/AddRecipeContext";

const useAddRecipeContext = () => {
  return useContext(AddRecipeContext)
}

export default useAddRecipeContext