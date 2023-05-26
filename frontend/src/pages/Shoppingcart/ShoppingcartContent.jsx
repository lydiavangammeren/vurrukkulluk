import React from "react";
import "./ShoppingcartContent.css";

import { AiOutlineCheck } from "react-icons/ai";
import { RiDeleteBinLine } from "react-icons/ri";

const ShoppingcartContent = ({product, checked, checkedProduct, removeProduct, updateQuantity}) => {
  return (
    <tr
      className={
        "shoppingcart_info " + (checked ? "shoppingcart_checked" : "")
      }
    >
      <td className="shoppingcart_img">
        <img
          src={require("../../assets/images/" + product.image)}
          alt={product.image}
          width="100%"
          height="100%"
        />
      </td>
      <td>
        <h3>{product.title}</h3>
        <p>{product.desc}</p>
      </td>
      <td>
        <input className="quantity_value" type="number" value={product.quantity}
               onChange={(e) => updateQuantity(product.id, e.target.value)} ></input>
      </td>
      <td>
        <p>
          <span className="price_value">&euro;&nbsp;</span>
          {product.price.toFixed(2)}
        </p>
      </td>
      <td>
        <AiOutlineCheck
          className="icon"
          color="#6e8722"
          size={20}
          onClick={() => checkedProduct(product.id)}
        />
      </td>
      <td>
        <RiDeleteBinLine
          className="icon"
          color="#b31714"
          size={20}
          onClick={() => removeProduct(product.id)}
        />
      </td>
    </tr>
  );
};

export default ShoppingcartContent;
