import React from "react";
import Rating from "./Rating";
import "../css/Details.css";
import { HiUsers } from 'react-icons/hi';
import { MdEuro } from 'react-icons/md';
import { VscFlame } from 'react-icons/vsc';
import {BsHeart} from 'react-icons/bs';


const Details = (props) => {
  const myStyle = {
    objectFit: "cover",
    objectPosition: "50%"
  }
{/* <img src={require("../assets/images/VeganBurger.jpg")} */ }
  return (
    <div className='details'>
      <div className='details_img'>
      <img src={require("../assets/images/" + props.recipe.image )}
      
        alt={props.recipe.image}
        width="100%"
        height="100%" 
        style={myStyle}/>
      </div>
      <div className="details_info">
        <div className="details_stats">
          <div className="details_person"><HiUsers color='#b31714'/> 4</div>
          <div className="details_price"><MdEuro color='#b31714'/>12,- </div>
          <div className="details_calorie"><VscFlame color='#b31714'/> 432</div>
        </div>
        <div className="title_rating">
          <div className="details_title">
            <h2>{props.recipe.title}</h2>
          </div>
          <div className="details_rating">
            <Rating />
          </div>
        </div>
        <div className="kitchen_type">
          <div className="details_kitchen">
            <span className="SpanFont">Keuken</span><span>{props.recipe.kitchen}</span>
          </div>
          <div className="details_type">
            <span className="SpanFont">Type</span><span>{props.recipe.type}</span>
          </div>
        </div>
        <p>
          Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque eu
          metus sem. Sed lobortis tempor arcu. Nulla id nulla in nibh dictum
          feugiat. Donec sed accumsan est, at accumsan velit. Fusce porttitor
          feugiat lectus, sit amet gravida elit egestas ac. Sed convallis sapien
          quis justo elementum consectetur. Maecenas tempus, turpis sed
          consectetur pellentesque, orci tortor consectetur nisl, sed posuere
          enim sem mattis diam. Sed leo magna, commodo et accumsan gravida,
          lobortis a diam. Curabitur dignissim finibus nunc in facilisis.
          Praesent at porta augue.
        </p>
        <p>
          Integer lacinia ipsum tellus, ut posuere risus consectetur in. Nullam
          ut elit nec eros rhoncus facilisis non a mauris. Nulla arcu sapien,
          rhoncus vitae suscipit quis, volutpat non eros. Nam lacinia felis
          ante, a cursus nisi varius ut. Nulla vel rhoncus mauris. Nunc lobortis
          volutpat leo. Integer consequat molestie elementum. Cras et tempus
          lorem.
        </p>
        <div className="details_buttons">
          <button className="ListButton">Op Lijst</button>
          <button className="FavouriteButton"><BsHeart size={30} color='#b31714' /></button>
        </div>
      </div>
    </div>
  );
};

export default Details;
