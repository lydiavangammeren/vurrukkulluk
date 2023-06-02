import React, { useState, useEffect } from "react";
import "./Carousel.css";
import Header from "./Header";
import MainMenu from "./MainMenu";
import { useAppContext, useRecipes } from "../../contexts";

const Carousel = () => {
  const [slide, setSlide] = useState(0);
  const [menuVisible, setMenuVisible] = useState(false);
  const {recipes} = useAppContext();

  const setImages = () => {
    const shuffled = recipes.sort(() => 0.5 - Math.random());
    
  }

  const data = []

  useEffect(() => {
    // console.log('Switch Image');
    const intervalId = setInterval(() => {
      // console.log(`Data.length: ${data.length}`)
      // console.log(`Slide at useEffect: ${slide}`)
      setSlide(prevIndex => (prevIndex + 1) % data.length);
    }, 8000);
    return () => clearInterval(intervalId);
  });

  return (
    <div className="carousel">
      {data.map((item, idx) => {
        // console.log(`Slide at images: ${slide}`)
        // console.log(`index: ${idx}`);
        // console.log(`Slide: ${slide}`);
        // console.log(`image name: ${item.src}`);
        return (
          <img
            src={require(`../../assets/images/${item.src}`)}
            alt={item.alt}
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