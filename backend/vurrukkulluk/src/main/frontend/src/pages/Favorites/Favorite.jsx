import React from 'react';
import Rating from '../../components/Rating/Rating';
import {useNavigate} from 'react-router-dom';
import { HiUsers } from 'react-icons/hi';
import { MdEuro } from 'react-icons/md';
import { VscFlame } from 'react-icons/vsc';
import { useAppContext } from '../../contexts';
import Category from '../../components/Category/Category';
import api from "../../lib/recipeAPI";

const Favorite = ({favorite, keepFavoritesCache}) => {
  console.log('Favorite: ', favorite)
  const recipe = favorite?.recipe;
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

  const removeFavorite = async () => {
    if(localStorage.getItem('user') === null) return console.log('Niet ingelogd');
    const token = localStorage.getItem('user') === null ? '' : 'Bearer ' + JSON.parse(localStorage.getItem('user')).token

    const headers = {
      'Content-Type': 'application/json',
      'Authorization': token 
    }

    try{
      const response = await api.delete(`/favorites/${favorite.id}`, {headers:headers})
      keepFavoritesCache(false);
    } catch (err) {
      console.log(err)
    }
  }


  
  const price = recipe.price / 100;
  const calories = recipe.calories / recipe.persons;
  return (
    //Change for BACKEND API !!!
    // <div className='Recipe' key={recipe.id} onClick={() => navigate(`/details/${recipe.id}`)}>
    <div className='Recipe' key={recipe.id} >
      
      <div className='recipe_img' onClick={() => navigate(`/details/${recipe.slug}`)}>
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
      <div className='recipe_desc' onClick={() => navigate(`/details/${recipe.slug}`)}>
        <div>
          {/* <Link to={`/details/${recipe.id}`}> */}
            {recipe.description}
          {/* </Link> */}
        </div>
      </div>
      <div className='recipe_bottom'>
        <div className='recipe_button'>
          <button onClick={() => removeFavorite()}>Verwijder</button>
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
