import React, {useEffect} from 'react'
import { useAppContext } from '../../contexts'
import { useDatabase } from '../../hooks';
import Favorite from './Favorite';
import "./Favorites.css";

const Favorites = () => {
  const user = JSON.parse(localStorage.getItem('user'));
  console.log('User: ', user)
  const [favorites, favoritesLoaded, keepFavoritesCache ] = useDatabase(`/favorites`)
  const {recipes} = useAppContext();

  const current = favorites?.filter((favorite)=>{
    return favorite.userId === user.id;
  });
  console.log('Current: ', current)

  const favRecipes = current?.map((fav) => {
    return {id: fav.id , recipe: recipes.find((recipe)=> {
      return recipe.id === fav.recipeId;
    })}
  })

  console.log('favRecipes: ', favRecipes)
  useEffect(() => {
    console.log('Favorites: ', favorites)
  }, [favorites, favoritesLoaded])
  
  return (
    <div className='favorites'>
      <h1>Favorieten</h1>
      <div className='favorites_list'>
        {favRecipes && favRecipes.map((favorite)=> {
          return <Favorite favorite={favorite} keepFavoritesCache={keepFavoritesCache}/>;
        })}
      </div>
      
    </div>
  )
}

export default Favorites
