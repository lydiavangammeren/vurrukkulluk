import React from "react";
import "./ShoppingcartContent.css";

import { AiOutlineCheck } from "react-icons/ai";
import { RiDeleteBinLine } from "react-icons/ri";

const ShoppingcartContent = (props) => {
  console.log(props);
  return (
    <div className="ShoppingcartContent">
      <div className="shoppingcart_img">
        <img
          src={require("../../assets/images/" + props.image)}
          alt={"props.image"}
          width="100%"
          height="100%"
        />
      </div>
      <div
        className={
          "shoppingcart_info " + (props.checked ? "shoppingcart_checked" : "")
        }
      >
        <h3>{props.title}</h3>
        <p>{props.desc}</p>
        <span className="quantity_span">Hoeveelheid:</span>
        <span className="quantity_value">{props.quantity}</span>
        <h3>{props.price}</h3>
        <AiOutlineCheck onClick={() => props.checkedProduct(props.id)} />

        <RiDeleteBinLine onClick={() => props.removeProduct(props.id)} />
      </div>
    </div>
  );
};

export default ShoppingcartContent;
