import React, { useState } from "react";
import {FaSearch} from 'react-icons/fa';
import { useNavigate } from "react-router-dom";
import { useAppContext } from "../../contexts";
import "./SearchBar.css";

const SearchBar = ({searchValue, setSearchValue}) => {
  // const navigate = useNavigate();
  
  // const {searchValue, setSearchValue} = useAppContext();
  // const [searchValue, setSearchValue] = useState('');

  const handleChange = (value) => {
    setSearchValue(value);
  }

  return (
      <div className="search_categories">
      {/* <FaSearch color="#b31714" size={16} id="search-icon" /> */}
      <input
        placeholder="Zoeken"
        value={searchValue}
        onChange={(e) => handleChange(e.target.value)}
      />
    </div>
  );
};

export default SearchBar;