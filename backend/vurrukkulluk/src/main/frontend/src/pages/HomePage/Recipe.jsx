import React from 'react';
import Rating from '../../components/Rating/Rating';
import {useNavigate} from 'react-router-dom';
import { HiUsers } from 'react-icons/hi';
import { MdEuro } from 'react-icons/md';
import { VscFlame } from 'react-icons/vsc';
import { useAppContext } from '../../contexts';
import Category from '../../components/Category/Category';

const Recipe = ({recipe}) => {
  const navigate = useNavigate();
  const { baseUrl } = useAppContext();
  // const [image, imageLoaded] = useDatabase(`image/${recipe.imgid}`);
  // const [image, imageLoaded] = useDatabase(`image/1`);

  const renderImage = () => {
    // if(imageLoaded){
      // console.log(`recipe Image: ${image.src}`);
      return (
        // <div></div>
        <img 
          // src={require(`../../assets/images/${image.src}`)}
          src={baseUrl + recipe.imageId}
          // src={'/images/EggsAndVeggies.jpg'}
          alt={recipe.title}
          width="100%"
          height="auto"
        />
      )
    }
  // }
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
        {/* {recipe.categories.forEach((category, index) => {
          console.log('CATEGORY' + index)
          if(index < 4){
            console.log('category below 5');
            <Category name={category.name} />
          }
        })} */}
        {recipe.categories.map((category, index)=> {
          if(index < 4){
            return (
              <Category name={category.name} />
            )
          }
        }) }
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

export default Recipe
