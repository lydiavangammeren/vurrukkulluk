import React from "react";


const Ingredient = (props) => {
  return (
    <div>
      <h3>{props.title}</h3>
      <p>{props.desc}</p>
      <span className="quantity_span">Hoeveelheid:</span>
      <span className="quantity_value">{props.quantity}</span>
    </div>
  );
};

export default Ingredient;
