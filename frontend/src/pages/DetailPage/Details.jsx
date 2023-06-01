import React from "react";
import Rating from "../../components/Rating";
import { HiUsers } from 'react-icons/hi';
import { MdEuro } from 'react-icons/md';
import { VscFlame } from 'react-icons/vsc';
import {BsHeart} from 'react-icons/bs';


const Details = ({details}) => {

  // console.log(`Details: ${details}`)

  const title = details.title;
  const description = details.description;
  const image = details.image;
  const persons = details.persons;
  const price = details.price;
  const calories = details.calories;
  const kitchenRegion = details?.kitchenRegion?.name ?? '';
  const kitchenType = details?.kitchenType?.name ?? '';

  // const ingredients = details?.ingredients ?? [];

  // const price = ingredients.length > 0 && ingredients.reduce((currentTotal, ingredient) => {
  //   console.log(`Prijs: ${ingredient.article.price} en currentTotal: ${currentTotal}`)
  //   return ingredient.article.price + currentTotal;
  // }, 0);

  // const {title, description, image, kitchenRegion, kitchenType, persons, price, calories} = details;

  const myStyle = {
    objectFit: "cover",
    objectPosition: "50%"
  }

  return (
    <div className='details'>
      <div className='details_img'>
        {image && <img src={require(`../../assets/images/${image}`)}
                        alt={image}
                        width="100%"
                        height="100%" 
                        style={myStyle}/>
        }
      
      </div>
      <div className="details_info">
        <div className="details_stats">
          <div className=".icon-align"><HiUsers size={18} color='#b31714'/><span className="setFont">{persons}</span></div>
          <div className=".icon-align"><MdEuro size={18} color='#b31714'/><span className="setFont">{price}</span></div>
          <div className=".icon-align"><VscFlame size={18} color='#b31714'/><span className="setFont">{calories}</span></div>
        </div>

        <div className="title_rating">
          <div className="details_title">
            <span className="greenFont pretty">{title}</span>
          </div>
          <div className="details_rating">
            <Rating />
          </div>
        </div>
        <div className="kitchen_type">
          <div className="details_kitchen">
            <span className="SpanFont">Keuken</span><span>{kitchenRegion}</span>
          </div>
          <div className="details_type">
            <span className="SpanFont">Type</span><span>{kitchenType}</span>
          </div>
        </div>
        <div className="details_desc">
          <div className="details_desc_inner">
            <span>{description}</span>
          </div>
        </div>
        <div className="details_buttons">
          <button className="ListButton">Op Lijst</button>
          <button className="FavouriteButton"><BsHeart size={30} color='#b31714' /></button>
        </div>
      </div>
    </div>
  );
};

export default Details;
