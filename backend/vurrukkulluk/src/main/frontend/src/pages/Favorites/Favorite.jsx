import React from 'react';
import Rating from '../../components/Rating/Rating';
import {useNavigate} from 'react-router-dom';
import { HiUsers } from 'react-icons/hi';
import { MdEuro } from 'react-icons/md';
import { VscFlame } from 'react-icons/vsc';
import { useAppContext } from '../../contexts';
import Category from '../../components/Category/Category';

const Favorite = ({recipe}) => {
  const navigate = useNavigate();
  const { baseUrl } = useAppContext();;

  const renderImage = () => {
      return (
        <img 
          src={baseUrl + recipe.imageId}
          alt={recipe.title}
          width="100%"
          height="100%"
        />
      )
    }
  
  const price = recipe.price / 100;
  const calories = recipe.calories / recipe.persons;
  return (
    //Change for BACKEND API !!!
    // <div className='Recipe' key={recipe.id} onClick={() => navigate(`/details/${recipe.id}`)}>
    <div className='Recipe' key={recipe.id} onClick={() => navigate(`/details/${recipe.slug}`)}>
      
      <div className='recipe_img'>
        {renderImage()}
      </div>
      
      <div className='recipe_categories'>
        {recipe.categories.map((category, index)=> {
          if(index < 4){
            return (
              <Category name={category.name} />
            )
          }
        }) }
      </div>

      <div className='recipe_title_rating'>
        <div className='recipe_title'><h2>{recipe.title}</h2></div>
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
            <span>{price.toFixed(2)}</span>
          </div>
          <div className='icon-align'>
            <VscFlame size={18} color='#b31714'/> 
            <span>{calories.toFixed(0)}</span>
          </div>
           
        </div>
        
      </div>
    </div>
  )
}

export default Favorite
