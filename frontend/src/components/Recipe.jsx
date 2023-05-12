import React from 'react';
import Rating from './Rating';
import {Link} from 'react-router-dom';
import { HiUsers } from 'react-icons/hi';
import { MdEuro } from 'react-icons/md';
import { VscFlame } from 'react-icons/vsc';

import '../css/Recipe.css';

const Recipe = (props) => {

  return (
    <div className='Recipe'>
      {/* <Link to='details' params={{recipe: props.recipe}}> */}
      <Link to={`/details/${props.recipe.id}`}>
      <div className='recipe_img'>
        <img src={require("../assets/images/" + props.recipe.image )}
        alt={props.recipe.image}
        width="100%"
        height="auto" />
      </div>
      <div className='recipe_title_rating'>
        <div className='recipe_title'><h2>{props.recipe.title}</h2></div>
        <Rating />
      </div>
      <div className='recipe_desc'>
        <p>
        Integer lacinia ipsum tellus, ut posuere risus consectetur in. Nullam ut elit nec eros rhoncus facilisis 
        non a mauris. Nulla arcu sapien, rhoncus vitae suscipit quis, volutpat non eros. Nam lacinia felis ante, 
        a cursus nisi varius ut. Nulla vel rhoncus mauris. Nunc lobortis volutpat leo. Integer consequat molestie 
        elementum. Cras et tempus lorem.
        </p>
      </div>
      </Link>
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
