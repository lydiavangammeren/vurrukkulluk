import React, {useState, useEffect} from "react";
import {FaSearch} from 'react-icons/fa';
import { useNavigate } from "react-router-dom";
import api from "../../lib/recipeAPI";
// import { useRecipes } from '../../contexts/RecipesContext';
import { useAppContext } from "../../contexts";

const SearchBar = ({}) => {
  const navigate = useNavigate();
  const [items, setItems] = useState([]);
  // const [input, setInput] = useState("");
  
  const {searchValue, setSearchValue} = useAppContext();
  // const setRecipes = useRecipesUpdate();

  const handleChange = (value) => {
    setSearchValue(value);

    
    navigate('/');
  }

  // useEffect(() => {
  //   const fetchData = async () => {
  //     try{
  //       const response = await api.get('/recipes');
  //       setItems(response.data);
  //     } catch(err){
  //       if(err.response){
  //         //Not in the 200 response range
  //         console.log(err.response.data);
  //         console.log(err.response.status);
  //         console.log(err.response.headers);
  //       }else{
  //         console.log(`Error: ${err.message}`)
  //       }
  //     }
  //   }
  //   fetchData();
  // }, [])

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