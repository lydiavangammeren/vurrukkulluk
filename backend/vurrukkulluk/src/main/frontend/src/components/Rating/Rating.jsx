import React, { useState, useEffect } from 'react';
import {BsStarFill, BsStarHalf, BsStar} from 'react-icons/bs';
import usePostData from "../../hooks/usePostData";
import api from "../../lib/recipeAPI";
import { useDatabase } from '../../hooks';
import { useAppContext } from '../../contexts';

const Rating = ({rating, recipeId}) => {
  // console.log('Rating: ', rating)
  const [data, isLoaded, postData] = usePostData();
  // const [ratings, ratingsLoaded] = useDatabase();
  const {keepRecipeCache} = useAppContext();
  const [hoverRating, setHoverRating] = useState(null);
  const user = JSON.parse(localStorage.getItem('user'));

  const addRating = async (rating) => {
    if(!user) return console.log('Niet ingelogd');

    // const token = localStorage.getItem('user') === null ? '' : 'Bearer ' + JSON.parse(localStorage.getItem('user')).token

    // const headers = {
    //   'Content-Type': 'application/json',
    //   'Authorization': token 
    // }

    const body = {
      rating: rating,
      userId: JSON.parse(localStorage.getItem('user')).id,
      recipeId: recipeId
    }

    // try{
    //   const response = await api.put('/ratings', JSON.stringify(body), {headers:headers})
    //   console.log('rating response: ', response)
    // } catch(err){
    //   console.log('Rating is niet gewijzigd')
    // }

    postData('/ratings', body);
  }

  useEffect(()=>{
    if(isLoaded) keepRecipeCache(false);
    // if(isLoaded) {
    //   console.log("data: ", data)
    //   rating = data.payLoad;
    // }
  }, [isLoaded])

  const handleMouseEnter = (index) => {
    setHoverRating(index + 1);
  }

  const handleMouseLeave = () => {
    setHoverRating(null);
  }

  const renderStar = (index) => {
    const hover = hoverRating !== null ? hoverRating : rating;
    if (hover >= index + 1) {
      return <BsStarFill 
                onClick={() =>addRating(index+1)} 
                color='#eb8d1f' 
                className='star'
                onMouseEnter={() => handleMouseEnter(index)}
                onMouseLeave={handleMouseLeave}/>;
    } else if (hover >= index + 0.5) {
      return <BsStarHalf 
                onClick={() =>addRating(index+1)} 
                color='#eb8d1f' 
                className='star'
                onMouseEnter={() => handleMouseEnter(index)}
                onMouseLeave={handleMouseLeave}/>;
    } else {
      return <BsStar 
                onClick={() =>addRating(index+1)} 
                color='#eb8d1f' 
                className='star'
                onMouseEnter={() => handleMouseEnter(index)}
                onMouseLeave={handleMouseLeave}/>;
    }
  }

  return (
    <div className='Rating'>
      {[0,1,2,3,4].map((index)=> { return renderStar(index)})}
    </div>
  )
}

export default Rating
