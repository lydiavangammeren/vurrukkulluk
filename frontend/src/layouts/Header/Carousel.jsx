import React, { useState, useEffect } from "react";
import "./Carousel.css";
import Header from "./Header";
import MainMenu from "./MainMenu";
import { useAppContext } from "../../contexts";
import { useLocation } from "react-router-dom";

const Carousel = () => {
  const [slide, setSlide] = useState(0);
  const [menuVisible, setMenuVisible] = useState(false);
  const {recipes} = useAppContext();

  const getImages = (array) => {
    // console.log('getImages: ' + array);
    return array.map(obj => obj.image);
  }

  const setImages = () => {
    // const shuffled = recipes.sort(() => 0.5 - Math.random());
    const shuffled = [...recipes].sort(() => 0.5 - Math.random());
    return getImages(shuffled.slice(0,5));
  }

  const data = setImages();

  useEffect(() => {
    // console.log('Switch Image');
    const intervalId = setInterval(() => {
      setSlide(prevIndex => (prevIndex + 1) % data.length);
    }, 8000);
    return () => clearInterval(intervalId);
  });

  const location = useLocation();
  console.log('Carousel location: ' + location.pathname);

  return (
    <div className="carousel">
      {data.map((item, idx) => {
        return (
          <img
            src={require(`../../assets/images/${item}`)}
            alt={item}
            key={idx}
            className={slide === idx ? "slide" : "slide slide-hidden"}
          />
        );
      })}

      <Header setVisible={setMenuVisible} />
      {/* <MainMenu visible={menuVisible} setVisible={setMenuVisible}/> */}
      <div className={menuVisible ? 'main-menu menu-visible' : 'main-menu menu-gone'} onClick={() =>setMenuVisible(false)}>
        <MainMenu />
      </div>

      <span className="indicators">
        {data.map((_, idx) => {
          // console.log(`Slide at indicators: ${slide}`)
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