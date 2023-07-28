import React, { useState, useEffect } from "react";
import "./Carousel.css";
import Header from "./Header";
import MainMenu from "./MainMenu";
import { useAppContext } from "../../contexts";
import { useLocation } from "react-router-dom";
import { useDatabase } from "../../hooks";
import useGetImages from "../../hooks/useGetImages";

const Carousel = () => {
  const [slide, setSlide] = useState(0);
  const [menuVisible, setMenuVisible] = useState(false);
  const {recipes, bannerImages, detailImage, baseUrl, exampleImage, setExampleImage} = useAppContext();

  const location = useLocation();
  const getImages = () => {
    if(location.pathname.substring(0,8) === '/details') {
      return detailImage ? [detailImage] : []; 
    } else {
      if(location.pathname.substring(0,10) !== '/addrecipe') setExampleImage(null);
      return bannerImages;
    }
    
  }
  const data = getImages();

  useEffect(() => {
    const intervalId = setInterval(() => {
      setSlide(prevIndex => (prevIndex + 1) % data.length);
    }, 8000);
    // console.log('Carousel UseEffect');
    return () => clearInterval(intervalId);
  });


  return (
    <div className="carousel">
      {exampleImage === null ? data.map((image, index) => {
        // console.log('Carousel image: ' + image);
        return (
          <img
            src={baseUrl + image}
            key={index}
            className={slide === index ? "slide" : "slide slide-hidden"}
          />
        )
      }) : <img src={exampleImage} style={{height:'100%'}}/>}
      <Header setVisible={setMenuVisible} />
      <div className={menuVisible ? 'main-menu menu-visible' : 'main-menu menu-gone'} onClick={() =>setMenuVisible(false)}>
        <MainMenu />
      </div>

      <span className="indicators">
        {data.length > 1 && exampleImage === null && data.map((_, idx) => {
          return (
            <button
              key={idx}
              className={
                slide === idx ? "indicator" : "indicator indicator-inactive"
              }
              onClick={() => setSlide(idx)}
            ></button>
          );
        })}
      </span>
    </div>
  );
};

export default Carousel;