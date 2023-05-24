import React, {useState} from "react";
import {FaSearch} from 'react-icons/fa';

import api from "../../lib/recipeAPI";

const SearchBar = ({setResults}) => {
  const [input, setInput] = useState("");

  const fetchData = async (value) => {
    try{
      const response = await api.get('/recipes');
      setResults(response.data.filter((recipe)=> {
        return (
          value &&
          recipe &&
          recipe.title &&
          recipe.title.toLowerCase().includes(value)
        )
      }));

    } catch(err){
      if(err.response){
        //Not in the 200 response range
        console.log(err.response.data);
        console.log(err.response.status);
        console.log(err.response.headers);
      }else{
        console.log(`Error: ${err.message}`)
      }
    }
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
      <FaSearch color="#b31714" size={20} id="search-icon" />
      <input
        placeholder="Type to search..."
        value={input}
        onChange={(e) => handleChange(e.target.value)}
      />
    </div>
  );
};

export default SearchBar;
