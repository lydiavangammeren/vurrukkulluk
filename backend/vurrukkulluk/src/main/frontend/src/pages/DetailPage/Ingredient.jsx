import React from "react";
import { useAppContext } from "../../contexts";

const Ingredient = ({ingredient, persons, defPersons}) => {
  const article = ingredient.articleunit.article; // new
  const title = article.name;
  const description = article.description;
  const image = article?.image ?? '';
  const imageId = article.imageId;
  const quantity = (ingredient.amount / defPersons) * persons;
  const unit = ingredient?.articleunit.unit.name ?? '';
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
