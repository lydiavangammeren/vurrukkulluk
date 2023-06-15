import "./SearchResultsList.css";
import { SearchResult } from "./SearchResult";

export const SearchResultsList = ({ results, setSearchValue }) => {
  return (
    <div className="ingredients_results">
      {results.map((result, id) => {
        return <SearchResult result={result} key={id} setSearchValue={setSearchValue}/>;
      })}
    </div>
  );
};