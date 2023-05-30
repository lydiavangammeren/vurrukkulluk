import React, {useState, useEffect} from "react";
import {FaSearch} from 'react-icons/fa';
import { useNavigate } from "react-router-dom";
import api from "../../lib/recipeAPI";
import { useRecipes, useRecipesUpdate } from '../../contexts/RecipesContext';

const SearchBar = ({}) => {
  const navigate = useNavigate();
  const [items, setItems] = useState([]);
  const [input, setInput] = useState("");
  
  const recipes = useRecipes();
  const setRecipes = useRecipesUpdate();

  const handleChange = (value) => {
    setInput(value);
    if(!value) return setRecipes(items);
    setRecipes(items.filter((recipe)=> {
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
    
    navigate('/');
  }

  useEffect(() => {
    const fetchData = async () => {
      try{
        const response = await api.get('/recipes');
        setItems(response.data);
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
    fetchData();
  }, [])

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