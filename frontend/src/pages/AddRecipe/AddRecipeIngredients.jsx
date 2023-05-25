import React, { useState, useEffect } from "react";
import api from "../../lib/recipeAPI";
import Ingredient from "./Ingredient";
import "./AddRecipeIngredients.css";

const AddRecipeIngredients = () => {
  const [IngredientsList, setIngredientsList] = useState([]);
  useEffect(() => {
    const getData = async () => {
      try {
        const response = await api.get("/products");
        setIngredientsList(response.data);
      } catch (err) {
        if (err.response) {
          console.log(err.response.data);
          console.log(err.response.status);
          console.log(err.response.headers);
        } else {
          console.log(`Error: ${err.message}`);
        }
      }
    };
    getData();
  }, []);

  const [image, setImage] = useState("null");
  const [title, setTitle] = useState("");
  const [text, setText] = useState("");
  const [Hoeveelheid, setHoeveelheid] = useState("");

  const handleImageChange = (event) => {
    const file = event.target.files[0];
    setImage(file);
  };
  const handleTitleChange = (event) => {
    setTitle(event.target.value);
  };
  const handleTextChange = (event) => {
    setText(event.target.value);
  };
  const handleHoeveelheidChange = (event) => {
    setHoeveelheid(event.target.value);
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    console.log("Recipe ingredients:", { image, title, text, Hoeveelheid });

    setImage("null");
    setTitle("");
    setText("");
    setHoeveelheid("");
  };

  return (
    <div>
      <h1 className="ing-header">Ingredients</h1>
      <div className="AddIngredients">
        <div className="IngredientsList">
          {IngredientsList.map((product) => (
            <Ingredient
              title={product.title}
              desc={product.desc}
              quantity={product.quantity}
            />
          ))}
        </div>
        <form className="IngredientForm" onSubmit={handleSubmit}>
          <div className="ingredients-title">
            <div className="title-input">
              <label htmlFor="title">Title:</label>
              <input
                type="text"
                id="title"
                value={title}
                onChange={handleTitleChange}
                required
              />
            </div>
          </div>
          <div className="ingredients-text">
            <div className="text-input">
              <label htmlFor="text">Text:</label>
              <textarea
                id="text"
                value={title}
                onChange={handleTextChange}
                required
              />
            </div>
          </div>
          <div className="Hoeveel">
            <div className="hoeveel-input">
              <label htmlFor="hoeveelheid">Hoeveelheid:</label>
              <input
                type="text"
                id="hoeveelheid"
                value={Hoeveelheid}
                onChange={handleHoeveelheidChange}
                required
              />
            </div>
          </div>
        </form>
      </div>
    </div>
  );
};

export default AddRecipeIngredients;
