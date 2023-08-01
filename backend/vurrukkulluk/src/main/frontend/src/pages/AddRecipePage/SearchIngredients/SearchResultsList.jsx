// import "./SearchResultsList.css";
import { SearchResult } from "./SearchResult";
import {AddNewArticle} from "./AddNewArticle";
import AddNew from "./AddNew";
import { useEffect } from "react";

export const SearchResultsList = ({ results, setSearchValue, newArticles, setNew }) => {
  // console.log('searchResultsList: ', results)

  useEffect(() => {
    if(!newArticles) return;
    document.querySelector("#add_article_modal").close()
  },[newArticles])

  return (
    <div className="ingredients_results">
      {results.map((result, id) => {
        return <SearchResult result={result} key={id} setSearchValue={setSearchValue} />;
      })}
      {results.length === 0 && 
      // <AddNewArticle />
        <AddNew />
      }
      <dialog id="add_article_modal">
        <AddNewArticle newArticles={newArticles} setNew={setNew}/>
      </dialog>

    </div>
  );
};