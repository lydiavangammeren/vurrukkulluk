import React from 'react'
import SearchBar from './SearchBar'
import { SlMenu } from 'react-icons/sl'
import { Link } from 'react-router-dom'

const Header = () => {
  return (
    <div className='Header'>
      <div className='header-logo'>
        <Link to={"/"} >
          <img src={require("../../assets/images/vurrukkulluk-logo.png")}
              alt='vurrukkulluk'
          />
        </Link>
      </div>
      <div className='header-left'>
        <SearchBar />
        <button className='hamburger'><SlMenu size={45} color="#eb8d1f"/></button>
      </div>
    </div>
  )
}

export default Header
