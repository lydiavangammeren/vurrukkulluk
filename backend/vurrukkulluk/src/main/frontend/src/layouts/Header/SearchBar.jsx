import React from "react";
import {FaSearch} from 'react-icons/fa';
import { useNavigate } from "react-router-dom";
import { useAppContext } from "../../contexts";

const SearchBar = () => {
  const navigate = useNavigate();
  
  const {searchValue, setSearchValue} = useAppContext();

  const handleChange = (value) => {
    setSearchValue(value);

    navigate('/');
  }

  return (
      <div className="input-wrapper">
      <FaSearch color="#b31714" size={20} id="search-icon" />
      <input
        placeholder="Zoeken"
        value={searchValue}
        onChange={(e) => handleChange(e.target.value)}
      />
    </div>
  );
};

export default SearchBar;