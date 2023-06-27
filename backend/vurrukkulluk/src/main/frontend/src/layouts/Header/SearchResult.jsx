import { Link } from "react-router-dom";
import "./SearchResult.css";

export const SearchResult = ({ title, id }) => {
  return (
    <Link to={`/details/${id}`} >
      <div className="search-result">
        {title}
      </div>
    </Link>
  );
};