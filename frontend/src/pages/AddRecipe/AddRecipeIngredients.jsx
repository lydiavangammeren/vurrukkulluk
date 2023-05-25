import React, { useState, useEffect } from "react";
import "./AddRecipeIngredients.css";

const AddRecipeIngredients = () => {
  const [title, setTitle] = useState("");
  const [text, setText] = useState("");
  const [Hoeveelheid, setHoeveelheid] = useState("");

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
    console.log("Recipe ingredients:", { title, text, Hoeveelheid });

    setTitle("");
    setText("");
    setHoeveelheid("");
  };

  return (
    <div>
      <h1 className="ing-header">Ingredients</h1>

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
  );
};

export default AddRecipeIngredients;
