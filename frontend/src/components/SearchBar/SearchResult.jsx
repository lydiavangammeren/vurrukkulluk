import { Link } from "react-router-dom";
import "./SearchResult.css";
import useAddRecipeContext from "../../hooks/useAddRecipeContext";

export const SearchResult = ({ title, id }) => {
  const { addCategory } = useAddRecipeContext();

  return (
    // <Link to={`/details/${id}`} >
      <div className="search-result"> 
        {title}
        <button onClick={addCategory} value={id}>Add</button>
      </div>
    // </Link>
  );
};