import "./SearchResult.css";
import useAddRecipeContext from "../../../hooks/useAddRecipeContext";

export const SearchResult = ({ title, id }) => {
  const { addCategory } = useAddRecipeContext();

  return (
      <div className="search-result"> 
        {title}
        <button onClick={addCategory} value={id}>Add</button>
      </div>
  );
};