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
  const {recipes, detailImage} = useAppContext();

  const getImages = () => {
    if(location.pathname.substring(0,8) === '/details'){
      console.log('DETAILPAGE! : ' + detailImage);
      return detailImage;
    } else {
      console.log('NOT DETAILPAGE!');
      return getRandomImages();
      // return [2, 3];
    }
  }

  const getRandomImages = () => {
    const shuffled = [...recipes].sort(() => 0.5 - Math.random());
    return shuffled.slice(0,5).map(obj => obj.imgid);
  }

  const location = useLocation();
   
  const data = getImages();
  console.log('Data: ' + data);

  useEffect(() => {
    const intervalId = setInterval(() => {
      setSlide(prevIndex => (prevIndex + 1) % data.length);
    }, 8000);
    return () => clearInterval(intervalId);
  });

  // Get the images/detailimage from the database.
  const [images, imagesLoaded] = useGetImages('/image', data);

  return (
    <div className="carousel">
      {imagesLoaded && images.map((image, index) => {
        console.log('Carousel image: ' + index);
        return (
          <img
            src={require(`../../assets/images/${image.src}`)}
            alt={image.src}
            key={index}
            className={slide === index ? "slide" : "slide slide-hidden"}
          />
        )
      })}
      <Header setVisible={setMenuVisible} />
      <div className={menuVisible ? 'main-menu menu-visible' : 'main-menu menu-gone'} onClick={() =>setMenuVisible(false)}>
        <MainMenu />
      </div>

      <span className="indicators">
        {data.map((_, idx) => {
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