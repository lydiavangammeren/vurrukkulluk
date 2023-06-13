import "./SearchResultsList.css";
import { SearchResult } from "./SearchResult";

export const SearchResultsList = ({ results }) => {
  return (
    <div className="ingredients_list">
      {results.map((result, id) => {
        return <SearchResult title={result.name} id={result.id} key={id} />;
      })}
    </div>
  );
};