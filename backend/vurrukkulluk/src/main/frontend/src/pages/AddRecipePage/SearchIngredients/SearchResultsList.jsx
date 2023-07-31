// import "./SearchResultsList.css";
import { SearchResult } from "./SearchResult";
import {AddNewArticle} from "./AddNewArticle";
import AddNew from "./AddNew";

export const SearchResultsList = ({ results, setSearchValue }) => {
  return (
    <div className="ingredients_results">
      {results.map((result, id) => {
        return <SearchResult result={result} key={id} setSearchValue={setSearchValue}/>;
      })}
      {results.length === 0 && 
      // <AddNewArticle />
        <AddNew />
      }
      <dialog id="add_article_modal">
        <AddNewArticle />
      </dialog>

    </div>
  );
};