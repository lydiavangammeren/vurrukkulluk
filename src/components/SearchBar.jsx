import React from 'react'
import magnifyingglass from '../assets/images/magnifyingglass.png'


const SearchBar = () => {
    return(
    <div className="form-container">
    <form className="form">
        {/* <img src={require("../assets/images/magnifyingglass.png")} alt="magnifying glass" width="5%" height="5%"/> */}
        <input id="search" style={ {backgroundImage: `url(${magnifyingglass})`,
  backgroundRepeat: 'no-repeat',
  backgroundPosition: '2px 3px',
  border: 'hidden',
  borderRadius: '12px', 
  textIndent: '17px'}} type="text" className="input" placeholder="Zoeken"/>        
    </form></div>)

};

export default SearchBar;