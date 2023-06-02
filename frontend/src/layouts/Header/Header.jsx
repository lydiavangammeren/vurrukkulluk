import React, {useState} from 'react'
import SearchBar from './SearchBar'
import { SlMenu } from 'react-icons/sl'
import { Link } from 'react-router-dom'
import { SearchResultsList } from './SearchResultsList'

const Header = ({setVisible}) => {
  // const [results, setResults] = useState([]);

  return (
    <div className='Header'>
      <div className='header-logo'>
        <Link to={"/"} >
          <img src={require("../../assets/images/vurrukkulluk-logo.png")}
              alt='vurrukkulluk'
          />
        </Link>
      </div>
      <div className='header-right'>
        <div className='searchBar'>
          <SearchBar />
          {/* {results && results.length > 0 && <SearchResultsList results={results} />} */}
        </div>

        <button className='hamburger' onClick={() => setVisible(true)}> 
          <SlMenu size={45} color="#eb8d1f"/>
        </button>
      </div>
    </div>
  )
}

export default Header
