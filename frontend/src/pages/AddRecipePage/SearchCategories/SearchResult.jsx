import "./SearchResult.css";
import useAddRecipeContext from "../../../hooks/useAddRecipeContext";

export const SearchResult = ({ title, id, setSearchValue }) => {
  const { addCategory } = useAddRecipeContext();

  const handleAdd = e => {
    const value = e.target.value;

    addCategory(value);

    setSearchValue('');
  }

  return (
      <div className="category_result"> 
        {title}
        <button name="categories" onClick={handleAdd} value={id}>Add</button>
      </div>
  );
};