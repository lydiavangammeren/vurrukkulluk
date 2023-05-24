import React, {useState} from "react";
import {FaSearch} from 'react-icons/fa';

const SearchBar = ({search, setSearch}) => {
  const [input, setInput] = useState("");

  const fetchData = (value) => {

  }

  const handleChange = (value) => {
    setInput(value);
    fetchData(value);
  }

  return (
      // <div>

      //   <input id="search" 
      //          type="search" 
      //          className="input" 
      //          placeholder="Zoeken" 
      //          value={search}
      //          onChange={e => setSearch(e.target.value)}
      //   />
      // </div>

      <div className="input-wrapper">
      <FaSearch id="search-icon" />
      <input
        placeholder="Type to search..."
        value={input}
        onChange={(e) => handleChange(e.target.value)}
      />
    </div>
  );
};

export default SearchBar;
