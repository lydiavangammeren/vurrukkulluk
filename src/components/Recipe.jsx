import React from 'react';
import Rating from './Rating';

const Recipe = (props) => {
  
  return (
    <div className='Recipe'>
      <div className='recipe_img'>
        <img src={require("../assets/images/" + props.image )}
        alt={props.image}
        width="100%"
        height="100%" />
      </div>
      <div className='recipe_title_rating'>
        <div className='recipe_title'><h2>{props.title}</h2></div>
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
      <div className='recipe_bottom'>
        <div className='recipe_button'>
          <button>Smullen</button>
        </div>
        <div className='recipe_stats'>
          <span>Person: 4 / Price: €12,- / KC: 432</span>
        </div>
        
      </div>
    </div>
  )
}

export default Recipe
