import React from "react";
import { useAppContext } from "../../contexts";

const Ingredient = ({ingredient}) => {
  const title = ingredient.article.name;
  const description = ingredient.article.description;
  const image = ingredient?.article?.image ?? '';
  const imageId = ingredient.article.imageId;
  const quantity = ingredient.amount;
  const unit = ingredient?.article?.unit ?? '';
  const { baseUrl } = useAppContext();

  const renderImage = () => {
    return (
      <img 
            // src={require(`../../assets/images/${image}`)} 
            src={baseUrl + imageId}
            alt={image}
        />

    )
    
  }
  
  return (
    <div className="ingredient">
      <div className="ingredient_img">
        {renderImage()}
        {/* {image &&
          <img 
            // src={require(`../../assets/images/${image}`)} 
            src={baseUrl + imageId}
            alt={image}
          />
        } */}
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
