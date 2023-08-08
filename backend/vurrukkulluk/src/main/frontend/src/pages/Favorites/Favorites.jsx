import React, {useEffect} from 'react'
import { useAppContext } from '../../contexts'
import { useDatabase } from '../../hooks';
import Favorite from './Favorite';

const Favorites = () => {
  const user = JSON.parse(localStorage.getItem('user'));
  console.log('User: ', user)
  const [favorites, favoritesLoaded ] = useDatabase(`/favorites`)
  const {recipes} = useAppContext();

  const current = favorites?.filter((favorite)=>{
    return favorite.userId === user.id;
  });
  console.log('Current: ', current)

  useEffect(() => {
    console.log('Favorites: ', favorites)
  }, [favorites, favoritesLoaded])
  
  return (
    <div className='favorites'>
      <h1>Favorieten</h1>
      <div className='favorites_list'>
        {current && current.map((recipe)=> {
          return <Favorite recipe={recipe} />;
        })}
      </div>
      
    </div>
  )
}

export default Favorites
