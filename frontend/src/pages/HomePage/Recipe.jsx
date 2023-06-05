import React from 'react';
import Rating from '../../components/Rating/Rating';
import {useNavigate} from 'react-router-dom';
import { HiUsers } from 'react-icons/hi';
import { MdEuro } from 'react-icons/md';
import { VscFlame } from 'react-icons/vsc';

const Recipe = ({recipe}) => {
  const navigate = useNavigate();
  
  return (
    //Change for BACKEND API !!!
    // <div className='Recipe' key={recipe.id} onClick={() => navigate(`/details/${recipe.id}`)}>
    <div className='Recipe' key={recipe.id} onClick={() => navigate(`/details/${recipe.slug}`)}>
      
      <div className='recipe_img'>
      {/* <Link to={`/details/${recipe.id}`}> */}
        {recipe.image && <img src={require(`../../assets/images/${recipe.image}`)}
                      alt={recipe.image}
                      width="100%"
                      height="auto" />
        }
      {/* </Link> */}
      </div>
      <div className='recipe_title_rating'>
      {/* <Link to={`/details/${recipe.id}`}> */}
        <div className='recipe_title'><h2>{recipe.title}</h2></div>
      {/* </Link> */}
      <Rating />
      </div>
      <div className='recipe_desc'>
        <div>
          {/* <Link to={`/details/${recipe.id}`}> */}
            {recipe.description}
          {/* </Link> */}
        </div>
      </div>
      <div className='recipe_bottom'>
        <div className='recipe_button'>
          <button onClick={() => navigate(`/details/${recipe.id}`)}>Smullen</button>
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
