import React, { useEffect, useState } from "react";
import Rating from "../../components/Rating";
import { HiUsers } from 'react-icons/hi';
import { MdEuro } from 'react-icons/md';
import { VscFlame } from 'react-icons/vsc';
import {BsHeart} from 'react-icons/bs';

import { useDatabase } from "../../hooks";
import { useAppContext, useShopContext, SL_ACTION } from "../../contexts";
import Category from "../../components/Category/Category";
import usePostData from "../../hooks/usePostData";
import useLocalStorage from "../../hooks/useLocalStorage"

const Details = ({details, persons, setPersons}) => {

  // console.log('Details: ', details)

  const { baseUrl } = useAppContext();
  const recipeId = details.id;
  const title = details.title;
  const description = details.description;
  const imageId = details.imageId;
  const rating = details.avgRating;
  // console.log(rating)
  // const persons = details.persons;
  
  const price = (details.price / 100) / details.persons * persons;
  const calories = details.calories / details.persons;
  const kitchenRegion = details?.kitchenRegion?.name ?? '';
  const kitchenType = details?.kitchenType?.name ?? '';
  const categories = details.categories;
  const user = JSON.parse(localStorage.getItem('user'));
  const userId = user?.id;
  const ingredients = details?.ingredients ?? [];

  const [data, isLoaded, postData] = usePostData()
  const {dispatch } = useShopContext();

  const addToList = () => {
    // console.log('click', ingredients)
    const articleIds = ingredients.map(ing => {
      return ing.articleunit.article.id;
    })
    // add persons to the dispatch 
    dispatch({type: SL_ACTION.ADD_RECIPE, payload: {recipeId: details.id, articleIds: articleIds}})
  }

  const renderImage = () => {
      return (
         <img 
            // src={require(`../../assets/images/${image.src}`)}
            src={baseUrl + imageId}
            alt={title}
            width="100%"
            height="100%" 
            style={myStyle}
          />
      )
    }

  const myStyle = {
    objectFit: "cover",
    objectPosition: "50%"
  }

  return (
    <div className='details'>
      <div className='details_img'>
        {renderImage()}
      </div>
      <div className="details_info">
        <div className="details_top">
          <div className="details_stats">
            {/* <div className=".icon-align"><HiUsers size={18} color='#b31714'/><span className="setFont">{persons}</span></div> */}
            <div className=".icon-align"><HiUsers size={18} color='#b31714'/><input type="number" min={1} value={persons} onChange={(e)=>setPersons(e.target.value)}/></div>
            <div className=".icon-align"><MdEuro size={18} color='#b31714'/><span className="setFont">{price.toFixed(2)}</span></div>
            <div className=".icon-align"><VscFlame size={18} color='#b31714'/><span className="setFont">{calories.toFixed(0)}</span></div>
          </div>
        </div>

        <div className="title_rating">
          <div className="details_title">
            <span className="greenFont pretty">{title}</span>
          </div>
          <div className="details_rating">
            <Rating rating={rating} recipeId={recipeId}/>
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
        <div className="details_categories">
          {categories && categories.map((category, index) => {
            return (
              <Category name={category.name} key={index}/>
            )
          })}
        </div>
        <div className="details_buttons">
          <button className="ListButton" onClick={addToList}>Op Lijst</button>
          {user &&
          <button
          className="FavouriteButton"
          onClick={() => {postData('/favorites', {userId: user.id, recipeId: recipeId })}}
          ><BsHeart size={30} color='#b31714' /></button>}
        </div>
      </div>
    </div>
  );
};

export default Details;
