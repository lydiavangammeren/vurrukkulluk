import React from 'react'
import { Link } from 'react-router-dom'

const MainMenu = () => {
  const user = localStorage.getItem('user');
  return (
    // <div className={visible ? 'main-menu menu-visible' : 'main-menu menu-gone'} onClick={setVisible(false)}>
    <>
      <div className='menu-logo'>
        <img src={"/images/vurrukkulluk-logo.png"}
              alt='vurrukkulluk'
        />
      </div>
      <div className='menu-links'>
        {user && 
        <Link to={'/addrecipe'} >
          <div className='menu-link'>
            <span>Voeg Recept toe</span>
          </div>
        </Link>}
        {user && <Link to={'/favorites'} >
        <div className='menu-link'>
          <span>Mijn Favorieten</span>
        </div>
        </Link>}
        <Link to={'/shoppingcart'} >
        <div className='menu-link'>
          <span>Mijn Boodschappenlijst</span>
        </div>
        </Link>
      </div>
    </>
    // </div>
  )
}

export default MainMenu
