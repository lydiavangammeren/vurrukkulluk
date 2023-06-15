import "./SearchResult.css";
import useAddRecipeContext from "../../../hooks/useAddRecipeContext";

export const SearchResult = ({ title, id, setSearchValue }) => {
  const { addItem } = useAddRecipeContext();

  const handleAdd = e => {
    const name = e.target.name
    const value = e.target.value
    addItem(name, value);
    setSearchValue('');
  }

  return (
      <div className="category_result"> 
        {title}
        <button name="categories" onClick={handleAdd} value={id}>Add</button>
      </div>
  );
};