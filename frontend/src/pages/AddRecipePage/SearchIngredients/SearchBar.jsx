import React from "react";
import {FaSearch} from 'react-icons/fa';
// import "./SearchBar.css";

const SearchBar = ({searchValue, setSearchValue}) => {

  const handleChange = (value) => {
    setSearchValue(value);
  }

  return (
      <div className="search_categories">
      <FaSearch color="#b31714" size={16} id="search-icon" />
      <input
        placeholder="Zoeken"
        value={searchValue}
        onChange={(e) => handleChange(e.target.value)}
      />
    </div>
  );
};

export default SearchBar;