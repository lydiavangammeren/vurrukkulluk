import React, { useEffect, useReducer } from "react";
import ShoppingCartItem from "./ShoppingCartItem";
import "./ShoppingCart.css";
import api from "../../lib/recipeAPI";
import { RiDeleteBinLine } from "react-icons/ri";
import { SC_ACTION } from "./ShoppingCartActions";
import { useDatabase } from "../../hooks";

import useLocalStorage from "../../hooks/useLocalStorage";

function reducer(state, action) {
  switch (action.type) {
    case SC_ACTION.POPULATE_LIST:
      return { ...state, products: action.payload.products}

    case SC_ACTION.REMOVE_ALL:
      return { ...state, products: []}

    case SC_ACTION.REMOVE_ITEM:
      return { ...state, products: state.products.filter((p) => p.id !== action.payload.id)}

    case SC_ACTION.UPDATE_QUANTITY:
      return { ...state, products: state.products.map((p) => {
        if (p.id === action.payload.id) {
          return { ...p, quantity: action.payload.quantity };
        } 
        return p;
      })}

    case SC_ACTION.TOGGLE_ITEM:
      if (state.checkedProductIds.includes(action.payload.id)) {
        return {...state, checkedProductIds: state.checkedProductIds.filter((i) => i !== action.payload.id)}
      } else {
        return { ...state, checkedProductIds: [...state.checkedProductIds, action.payload.id]}
      }
  

    default: 
      console.log("unknown action in reduce for shopping cart: " + action.type);
      return state
  }
}

const ShoppingCart = () => {
  /* video on useReducer: https://www.youtube.com/watch?v=kK_Wqx3RnHk */
  const [state, dispatch] = useReducer(reducer, { products: [], checkedProductIds: [] });

  // const [products, productsLoaded ] = useDatabase('/products');
  const [products, setProducts] = useLocalStorage('shoppinglist');
  
  useEffect(() => {
    const getData = async () => {
      try {
        const response = await api.get("/products");
        dispatch({ type: SC_ACTION.POPULATE_LIST, payload:{ products: response.data }});
      } catch (err) {
        if (err.response) {
          //Not in the 200 response range
          console.log(err.response.data);
          console.log(err.response.status);
          console.log(err.response.headers);
        } else {
          console.log(`Error: ${err.message}`);
        }
      }
    };
    getData();
  }, []);

  const uncheckedProducts = state.products.filter(
    (product) => !state.checkedProductIds.includes(product.id))

  const checkedProducts = state.products.filter(
    (product) => state.checkedProductIds.includes(product.id))

  const totalPrice = uncheckedProducts.reduce((acc, product) => {
      return acc + (product.quantity * product.price);
    }, 0);

  return (
    <div className="Shoppingcarts">
      <table>
        <thead>
          <tr>
            <th colSpan="6">
              <h1>Boodschappen</h1>
            </th>
          </tr>    
        </thead>
          <tbody>
            {uncheckedProducts.map((product) => (
              <ShoppingCartItem
                checked={false}
                key={product.id}
                product={product}
                dispatch={dispatch}
              />
            ))}
            {checkedProducts.map((product) => (
              <ShoppingCartItem
                checked={true}
                key={product.id}
                product={product}
                dispatch={dispatch}
              />
            ))}
          </tbody>
        <tfoot>
          <tr>
            <td colspan="3">
              <h2>Total</h2>
            </td>
            <td>
              <span className="price_value">&euro;&nbsp;</span>
              {totalPrice.toFixed(2)}
            </td>
            <td></td>
            <td>
              <RiDeleteBinLine
                className="icon"
                color="#b31714"
                size={20}
                onClick={() => dispatch({type: SC_ACTION.REMOVE_ALL})}
              />
            </td>
          </tr>
        </tfoot>
      </table>
    </div>
  );
};

export default ShoppingCart;
