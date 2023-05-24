import React, { useState } from "react";

const AddRecipeDetails = () => {
  const [aantalPersonen, setAantal] = useState("");
  const [image, setImage] = useState("null");
  const [title, setTitle] = useState("");
  const [keuken, setKeuken] = useState("");
  const [type, setType] = useState("");
  const [text, setText] = useState("");

  const handleAantalPersonenChange = (event) => {
    setAantal(event.target.value);
  };

  const handleImageChange = (event) => {
    const file = event.target.files[0];
    setImage(file);
  };

  const handleTitleChange = (event) => {
    setTitle(event.target.value);
  };
  const handleKeukenChange = (event) => {
    setTitle(event.target.value);
  };
  const handleTypeChange = (event) => {
    setTitle(event.target.value);
  };

  const handleTextChange = (event) => {
    setText(event.target.value);
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    console.log("Recipe submitted:", { aantalPersonen, image, title, text });
    setAantal("");
    setImage("null");
    setTitle("");
    setKeuken("");
    setType("");
    setText("");
  };

  return (
    <form onSubmit={handleSubmit}>
      <div>
        <label htmlFor="aantalPersonen">Aantal Personen</label>
        <input
          type="number"
          min={1}
          onChange={handleAantalPersonenChange}
          required
        />
      </div>
      <div>
        <label htmlFor="image">Image:</label>
        <input
          //look about the type of image//
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
        <label htmlFor="keuken">Keuken:</label>
        <input
          type="text"
          id="keuken"
          value={keuken}
          onChange={handleKeukenChange}
          required
        />
      </div>
      <div>
        <label htmlFor="title">Type:</label>
        <input
          type="text"
          id="type"
          value={type}
          onChange={handleTypeChange}
          required
        />
      </div>
      <div>
        <label htmlFor="text">Text:</label>
        <textarea id="text" value={text} onChange={handleTextChange} required />
      </div>
    </form>
  );
};

export default AddRecipeDetails;
