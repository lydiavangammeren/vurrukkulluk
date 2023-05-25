import React, {useState} from "react";
import {FaSearch} from 'react-icons/fa';

import api from "../../lib/recipeAPI";

const SearchBar = ({setResults}) => {
  // const [items, setItems] = useState([]);
  const [input, setInput] = useState("");



  const fetchData = async (value) => {
    try{
      const response = await api.get('/recipes');
      setResults(response.data.filter((recipe)=> {
        return (
          value &&
          recipe &&
          recipe.title && (
            recipe.title.toLowerCase().includes(value) ||
            recipe.kitchen.toLowerCase().includes(value) ||
            recipe.type.toLowerCase().includes(value) ||
            recipe.description.toLowerCase().includes(value)
          )
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
      <div className="input-wrapper">
      <FaSearch color="#b31714" size={20} id="search-icon" />
      <input
        placeholder="Zoeken"
        value={input}
        onChange={(e) => handleChange(e.target.value)}
      />
    </div>
  );
};

export default SearchBar;
