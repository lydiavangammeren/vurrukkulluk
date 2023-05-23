import React, {useEffect} from 'react';
import Recipe from './Recipe';

const Home = ({recipes, setImages}) => {

  useEffect(() => {
    let items = [];
    recipes.map((recipe) => {
      return items.push({src:recipe.image, alt:recipe.image})
    })
    // console.log(items);
    setImages(items);
  }, [recipes, setImages])

  return (
    <div className='Home'>
      {recipes.map((recipe, index) => {
        return (
          <Recipe recipe={recipe} />
        )
      })}
    </div>
  )
}

export default Home
