import React from "react";
import "./ShoppingcartContent.css";

import { AiOutlineCheck } from "react-icons/ai";
import { RiDeleteBinLine } from "react-icons/ri";

const ShoppingcartContent = (props) => {
  console.log(props);
  return (
    <tr
      className={
        "shoppingcart_info " + (props.checked ? "shoppingcart_checked" : "")
      }
    >
      <td className="shoppingcart_img">
        <img
          src={require("../../assets/images/" + props.image)}
          alt={"props.image"}
          width="100%"
          height="100%"
        />
      </td>
      <td>
        <h3>{props.title}</h3>
        <p>{props.desc}</p>
      </td>
      <td>
        <input className="quantity_value" value={props.quantity}></input>
      </td>
      <td>
        <p>
          <span className="price_value">&euro;&nbsp;</span>
          {props.price.toFixed(2)}
        </p>
      </td>
      <td>
        <AiOutlineCheck
          className="icon"
          color="#6e8722"
          size={20}
          onClick={() => props.checkedProduct(props.id)}
        />
      </td>
      <td>
        <RiDeleteBinLine
          className="icon"
          color="#b31714"
          size={20}
          onClick={() => props.removeProduct(props.id)}
        />
      </td>
    </tr>
  );
};

export default ShoppingcartContent;
