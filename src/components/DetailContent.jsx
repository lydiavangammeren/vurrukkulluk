import React from "react";
import Details from "./Details";
import "../css/DetailContent.css";
import { useParams } from "react-router-dom";
import TabWrapper from "./TabWrapper";

const DetailContent = () => {
  const { id } = useParams();
  const recipes = [
    {
      id: "1",
      title: "Eggs & Veggies",
      image: "EggsAndVeggies.jpg",
      kitchen: "Nederlands",
      type: "Vegatarisch",
    },
    {
      id: "2",
      title: "Vegan Burger",
      image: "VeganBurger.jpg",
      kitchen: "Amerikaans",
      type: "Vegan",
    },
    {
      id: "3",
      title: "Sushi Rolls",
      image: "SushiRolls.jpg",
      kitchen: "Japans",
      type: "Vis",
    },
    {
      id: "4",
      title: "Pizza Green",
      image: "PizzaGreen.jpg",
      kitchen: "Italiaans",
      type: "Vlees",
    },
  ];

  const recipe = recipes.find((x) => x.id === id);
  return (
    <div className="DetailContent">
      <div className="detailTop">
        {id}
        {recipe.kitchen}
        {/* <Details  /> */}
      </div>
      <div className="detailBottom">
        <TabWrapper />
      </div>
    </div>
  );
};

export default DetailContent;
