import React from "react";
import SearchBar from "./SearchBar";
import { Link } from "react-router-dom";

const HeroImage = () => {
  return (
    <>
      <div className="HeroImage">
        <div className="HeroImage-left">
          <Link to={'/'} >
            <img
              src={require("../../assets/images/vurrukkulluk-logo.png")}
              alt="vurrukkulluk logo"
              id="logo"
              width="90%"
            />
          </Link>
        </div>
        <div className="HeroImage-right">
          <div className="searchbar">
            <SearchBar />
          </div>
          <div id="hamburger">
            <img
              src={require("../../assets/images/hamburgermenu.png")}
              alt="hamburger"
              width="25px"
              height="25px"
            />
          </div>
        </div>
      </div>
    </>
  );
};

export default HeroImage;
