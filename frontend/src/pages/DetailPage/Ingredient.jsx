import React from "react";

const Ingredient = ({ingredient}) => {
  
  return (
    <div className="ingredient">
      <div className="ingredient_img">
        <img src={require(`../../assets/images/${ingredient.image}`)} alt={ingredient.image}/>
      </div>
      <div className='ingredient_info'>
        <h3>{ingredient.title}</h3>
        <p>{ingredient.description}</p>
        <span className='quantity_span'>Hoeveelheid:</span><span className='quantity_value'>{ingredient.quantity}</span>
      </div>
    </div>
  );
};

export default Ingredient;
