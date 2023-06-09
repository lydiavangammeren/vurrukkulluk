import React, { useState } from "react";
import "./AddRecipeDetails.css";

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
    <div className="AddDetails">
      <h1 >Voeg hier uw recept info toe</h1>
      <form onSubmit={handleSubmit}>
        <div id="AantalDetail">
          <label htmlFor="aantalPersonen">Aantal Personen</label>
          <input
            type="number"
            min={1}
            onChange={handleAantalPersonenChange}
            required
          />
        </div>
        <div className="container">
          <div id="imageDetail">
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
            <div id="DetailTitle">
              <div className="user-input">
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
            <div className="infoDetail">
              <div className="user-input">
                <div id="keuken">
                  <label htmlFor="keuken">Keuken:</label>
                  <input
                    className="input-style"
                    type="text"
                    id="keuken"
                    value={keuken}
                    onChange={handleKeukenChange}
                    required
                  />
                </div>
              </div>
              <div id="titleDetail">
                <div className="user-input">
                  <label htmlFor="title">Type:</label>
                  <input
                    className="input-style"
                    type="text"
                    id="type"
                    value={type}
                    onChange={handleTypeChange}
                    required
                  />
                </div>
              </div>
            </div>
            <div>
              <label className="text-label" htmlFor="text">
                Text:
              </label>
              <textarea
                className="text"
                id="text"
                value={text}
                onChange={handleTextChange}
                required
              />
            </div>
          </div>
        </div>
      </form>
    </div>
  );
};

export default AddRecipeDetails;
