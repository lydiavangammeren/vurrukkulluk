import "./SearchResultsList.css";
import { SearchResult } from "./SearchResult";
import useAddRecipeContext from "../../../hooks/useAddRecipeContext";

export const SearchResultsList = ({ results, setSearchValue }) => {
  const { data } = useAddRecipeContext();
  // console.log('results: ', results)
  // console.log('categories: ', data.categories)
  const filteredResults = results.filter(result => !data.categories.includes(String(result.id)))
  // console.log('filtered: ', filteredResults)
  return (
    <div className="categories_results">
      {filteredResults.map((result, id) => {
        return <SearchResult title={result.name} id={result.id} setSearchValue={setSearchValue} key={id} />;
      })}
    </div>
  );
};