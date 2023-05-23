import React from "react";

const Ingredient = ({image, title, description, quantity}) => {
  
  return (
    <div className="ingredient">
      <div className="ingredient_img">
        <img src={require(`../../assets/images/${image}`)} alt={image}/>
      </div>
      <div className='ingredient_info'>
        <h3>{title}</h3>
        <p>{description}</p>
        <span className='quantity_span'>Hoeveelheid:</span><span className='quantity_value'>{quantity}</span>
      </div>
    </div>
  );
};

export default Ingredient;
