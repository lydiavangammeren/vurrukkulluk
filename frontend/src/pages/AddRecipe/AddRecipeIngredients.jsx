import React, { useState } from "react";




<h1>ingredienten</h1>;
const AddRecipeIngredients = () => {
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
    <form onSubmit={handleSubmit}>
      <div>
        <label htmlFor="image">Image:</label>
        <input
          type="file"
          id="image"
          accept="image/*"
          onChange={handleImageChange}
          required
        />
      </div>
      <div>
        <label htmlFor="title">Title:</label>
        <input
          type="text"
          id="title"
          value={title}
          onChange={handleTitleChange}
          required
        />
      </div>
      <div>
        <label htmlFor="text">Text:</label>
        <textarea
          id="text"
          value={title}
          onChange={handleTextChange}
          required
        />
      </div>
      <div>
        <label htmlFor="title">Hoeveelheid:</label>
        <input
          type="text"
          id="title"
          value={Hoeveelheid}
          onChange={handleHoeveelheidChange}
          required
        />
      </div>
    </form>
  );
};

export default AddRecipeIngredients;
