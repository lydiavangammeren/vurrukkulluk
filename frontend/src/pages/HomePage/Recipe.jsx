import React from 'react';
import Rating from '../../components/Rating/Rating';
import {Link} from 'react-router-dom';
import { HiUsers } from 'react-icons/hi';
import { MdEuro } from 'react-icons/md';
import { VscFlame } from 'react-icons/vsc';

const Recipe = ({recipe}) => {
  
  return (
    <div className='Recipe' key={recipe.id}>
      {/* <Link to='details' params={{recipe: props.recipe}}> */}
      
      <div className='recipe_img'>
      <Link to={`/details/${recipe.id}`}>
        {recipe.image && <img src={require(`../../assets/images/${recipe.image}`)}
                      alt={recipe.image}
                      width="100%"
                      height="auto" />
        }
      </Link>
      </div>
      <div className='recipe_title_rating'>
      <Link to={`/details/${recipe.id}`}>
        <div className='recipe_title'><h2>{recipe.title}</h2></div>
        <Rating />
      </Link>
      </div>
      <div className='recipe_desc'>
      <Link to={`/details/${recipe.id}`}>
        <p>{recipe.description}</p>
      </Link>
      </div>
      <div className='recipe_bottom'>
        <div className='recipe_button'>
          <button>Smullen</button>
        </div>
        <div className='recipe_stats'>
          <div className='icon-align'>
            <HiUsers size={18} color='#b31714'/>
            <span>{recipe.persons}</span>
          </div>
          <div className='icon-align'>
            <MdEuro size={18} color='#b31714'/> 
            <span>{recipe.price}</span>
          </div>
          <div className='icon-align'>
            <VscFlame size={18} color='#b31714'/> 
            <span>{recipe.calories}</span>
          </div>
           
        </div>
        
      </div>
    </div>
  )
}

export default Recipe
