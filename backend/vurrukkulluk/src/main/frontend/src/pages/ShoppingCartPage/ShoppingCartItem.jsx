import React from "react";
import "./ShoppingCartItem.css";
import { SC_ACTION } from "./ShoppingCartActions";

import { AiOutlineCheck } from "react-icons/ai";
import { RiDeleteBinLine } from "react-icons/ri";
import { useAppContext } from "../../contexts";

const ShoppingCartItem = ({product, checked, dispatch}) => {
  const { baseUrl } = useAppContext();
  const article = product.article;
  return (
    <tr
      className={
        "shoppingcart_info " + (checked ? "shoppingcart_checked" : "")
      }
    >
      <td className="shoppingcart_img">
        <img
          src={baseUrl + article.imageId}
          alt={article.image}
          width="100%"
          height="100%"
        />
      </td>
      <td className="shoppingcart_desc">
        <h2>{article.name}</h2>
        <p>{article.description}</p>
      </td>
      <td>
        <input className="quantity_value" type="number" value={product.amount} min="0" max="999"
               onChange={(e) => dispatch({ type: SC_ACTION.UPDATE_QUANTITY, 
                                           payload: {id: article.id, quantity: e.target.value}})} ></input>
      </td>
      <td>
        <p>
          <span className="price_value">&euro;&nbsp;</span>
          {article.price.toFixed(2)}
        </p>
      </td>
      <td>
        <AiOutlineCheck
          className="icon"
          color="#6e8722"
          size={20}
          onClick={() => dispatch({ type: SC_ACTION.TOGGLE_ITEM, payload: {id: article.id}})}
        />
      </td>
      <td>
        <RiDeleteBinLine
          className="icon"
          color="#b31714"
          size={20}
          onClick={() => dispatch({ type: SC_ACTION.REMOVE_ITEM, payload: {id: article.id}})}
        />
      </td>
    </tr>
  );
};

export default ShoppingCartItem;
