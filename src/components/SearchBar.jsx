import React from "react";

const SearchBar = () => {
  return (
    <div className="form-container">
      <form className="form">
        <input id="search" type="text" className="input" placeholder="Zoeken" />
      </form>
    </div>
  );
};

export default SearchBar;
