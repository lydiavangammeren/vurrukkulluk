import React from "react";

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
      </div>
    </>
  );
};

export default HeroImage;
