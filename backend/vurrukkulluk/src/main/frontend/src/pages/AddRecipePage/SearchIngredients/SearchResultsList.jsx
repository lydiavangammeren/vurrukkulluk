// import "./SearchResultsList.css";
import { SearchResult } from "./SearchResult";
import {AddNewArticle} from "./AddNewArticle";

export const SearchResultsList = ({ results, setSearchValue }) => {

  return (
    <div className="ingredients_results">
      {results.map((result, id) => {
        return <SearchResult result={result} key={id} setSearchValue={setSearchValue}/>;
      })}
      {results.length === 0 && 
      <AddNewArticle />}
      {/* <AddNewArticle /> */}
    </div>
  );
};