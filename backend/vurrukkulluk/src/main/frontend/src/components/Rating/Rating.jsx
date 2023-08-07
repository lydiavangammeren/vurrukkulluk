import React from 'react';
import {BsStarFill, BsStarHalf, BsStar} from 'react-icons/bs';
import usePostData from "../../hooks/usePostData";

const Rating = ({rating, recipeId}) => {
  // console.log('Rating: ', rating)
  const [data, isLoaded, postData] = usePostData()

  const addRating = (rating) => {
    const body = {
      rating: rating,
      userId: JSON.parse(localStorage.getItem('user')).id,
      recipeId: recipeId
    }

    postData('/ratings', body);
  }

  const renderStar = (index) => {
    if (rating >= index + 1) {
      return <BsStarFill onClick={() =>addRating(index+1)} color='#eb8d1f' className='star'/>;
    } else if (rating >= index + 0.5) {
      return <BsStarHalf onClick={() =>addRating(index+1)} color='#eb8d1f' className='star'/>;
    } else {
      return <BsStar onClick={() =>addRating(index+1)} color='#eb8d1f' className='star'/>;
    }
  }

  return (
    <div className='Rating'>
      {[0,1,2,3,4].map((index)=> { return renderStar(index)})}
    </div>
  )
}

export default Rating
