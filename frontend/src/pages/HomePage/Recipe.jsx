import React from 'react';
import Rating from '../../components/Rating/Rating';
import {useNavigate} from 'react-router-dom';
import { HiUsers } from 'react-icons/hi';
import { MdEuro } from 'react-icons/md';
import { VscFlame } from 'react-icons/vsc';

import { useDatabase } from '../../hooks';

const Recipe = ({recipe}) => {
  const navigate = useNavigate();
  // const [image, imageLoaded] = useDatabase(`image/${recipe.imgid}`);
  // const [image, imageLoaded] = useDatabase(`image/1`);

  const renderImage = () => {
    // if(imageLoaded){
      // console.log(`recipe Image: ${image.src}`);
      return (
        // <div></div>
        <img 
          // src={require(`../../assets/images/${image.src}`)}
          src={`http://localhost:8080/image/${recipe.imageId}`}
          alt={recipe.title}
          width="100%"
          height="auto"
        />
      )
    }
  // }
  
  return (
    //Change for BACKEND API !!!
    // <div className='Recipe' key={recipe.id} onClick={() => navigate(`/details/${recipe.id}`)}>
    <div className='Recipe' key={recipe.id} onClick={() => navigate(`/details/${recipe.slug}`)}>
      
      <div className='recipe_img'>
        {renderImage()}
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
