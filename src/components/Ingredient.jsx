import React from "react";
import "../css/Ingredient.css";

const Ingredient = (props) => {
  return (
    <div className="ingredient">
      <div className="ingredient_img">
        <img src="" />
      </div>
      <div className="ingredient_info">
        <h3>{props.title}</h3>
        <p>{props.desc}</p>
        <span className="quantity_span">Hoeveelheid: </span>
        <span className="quantity_value">{props.quantity}</span>
      </div>
    </div>
  );
};

export default Ingredient;
