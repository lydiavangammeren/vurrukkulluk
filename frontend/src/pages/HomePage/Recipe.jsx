import React from 'react';
import Rating from '../../components/Rating/Rating';
import {Link} from 'react-router-dom';
import { HiUsers } from 'react-icons/hi';
import { MdEuro } from 'react-icons/md';
import { VscFlame } from 'react-icons/vsc';

const Recipe = ({id, image, title, desc}) => {
  
  return (
    <div className='Recipe' key={id}>
      {/* <Link to='details' params={{recipe: props.recipe}}> */}
      
      <div className='recipe_img'>
      <Link to={`/details/${id}`}>
        {image && <img src={require(`../../assets/images/${image}`)}
                      alt={image}
                      width="100%"
                      height="auto" />
        }
      </Link>
      </div>
      <div className='recipe_title_rating'>
      <Link to={`/details/${id}`}>
        <div className='recipe_title'><h2>{title}</h2></div>
        <Rating />
      </Link>
      </div>
      <div className='recipe_desc'>
      <Link to={`/details/${id}`}>
        <p>{desc}</p>
      </Link>
      </div>
      <div className='recipe_bottom'>
        <div className='recipe_button'>
          <button>Smullen</button>
        </div>
        <div className='recipe_stats'>
          <span>
            <HiUsers color='#b31714'/> 4 
            <MdEuro color='#b31714'/> 12,- 
            <VscFlame color='#b31714'/> 432
          </span>
           
        </div>
        
      </div>
    </div>
  )
}

export default Recipe
