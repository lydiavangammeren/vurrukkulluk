import React from 'react';
import {BsStarFill, BsStarHalf, BsStar} from 'react-icons/bs';

const Rating = ({rating}) => {
  
  
  return (
    <div className='Rating'>
      {/* <p style={{fontSize:'30px'}}>
        <span style={{color:'red'}}>*****</span>*
      </p> */}
      <BsStarFill color='#eb8d1f'/>
      <BsStarFill color='#eb8d1f'/>
      <BsStarFill color='#eb8d1f'/>
      <BsStarHalf color='#eb8d1f'/>
      <BsStar color='#eb8d1f'/>
      
    </div>
  )
}

export default Rating
