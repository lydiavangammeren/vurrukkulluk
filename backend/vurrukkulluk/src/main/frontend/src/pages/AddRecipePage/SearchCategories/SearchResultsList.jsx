import "./SearchResultsList.css";
import { SearchResult } from "./SearchResult";

export const SearchResultsList = ({ results, setSearchValue }) => {
  return (
    <div className="categories_results">
      {results.map((result, id) => {
        return <SearchResult title={result.name} id={result.id} setSearchValue={setSearchValue} key={id} />;
      })}
    </div>
  );
};