import React from "react";

const Ingredient = ({ingredient}) => {
  const title = ingredient.article.name;
  const description = ingredient.article.description;
  const image = ingredient?.article?.image ?? '';
  const quantity = ingredient.amount;
  const unit = ingredient?.article?.unit ?? '';
  
  return (
    <div className="ingredient">
      <div className="ingredient_img">
        {image &&
          <img src={require(`../../assets/images/${image}`)} alt={image}/>
        }
      </div>
      <div className='ingredient_info'>
        <h3>{title}</h3>
        <p>{description}</p>
        <span className='quantity_span'>Hoeveelheid:</span><span className='quantity_value'>{`${quantity} ${unit}`}</span>
      </div>
    </div>
  );
};

export default Ingredient;
