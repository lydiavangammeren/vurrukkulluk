import React from 'react'

const MainMenu = () => {
  return (
    // <div className={visible ? 'main-menu menu-visible' : 'main-menu menu-gone'} onClick={setVisible(false)}>
    <>
      <div className='menu-logo'>
        <img src={require("../../assets/images/vurrukkulluk-logo.png")}
              alt='vurrukkulluk'
        />
      </div>
      <div className='menu-links'>
        <ul>
          <li>Mijn Favorieten</li>
          <li>Mijn Boodschappenlijst</li>
        </ul>
      </div>
    </>
    // </div>
  )
}

export default MainMenu
