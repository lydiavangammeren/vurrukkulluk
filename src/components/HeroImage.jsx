import React from "react";
import SearchBar from "./SearchBar";

const HeroImage = () => {
  return (
    <>
      <div className="container">
        <img
          src={require("../assets/images/heroimage.jpg")}
          alt="hero"
          width="100%"
          height="600"
          id="img1"
        />
        <img
          src={require("../assets/images/vurrukkulluk-logo.png")}
          alt="vurrukkulluk logo"
          width="20%"
          height="20%"
          id="logo"
        />
        <div id="searchbar"><SearchBar/></div>
        <div id="hamburger"><img src={require("../assets/images/hamburger.jpg")} alt="hamburger" width="20%" height="20%"/></div>
        

      </div>
    </>
  );
};

export default HeroImage;
