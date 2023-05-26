import React, { useState, useEffect } from "react";
import ShoppingCartItem from "./ShoppingCartItem";
import "./ShoppingCart.css";
import api from "../../lib/recipeAPI";
import { RiDeleteBinLine } from "react-icons/ri";

const Shoppingcarts = () => {
  const [ProductList, setProductList] = useState([]);
  useEffect(() => {
    const getData = async () => {
      try {
        const response = await api.get("/products");
        setProductList(response.data);
        // console.log('recipes set');
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

  function removeProductWithId(id) {
    setProductList(ProductList.filter((p) => p.id !== id));
  }

  function updateQuantity(id, quantity) {
    setProductList(ProductList.map((p) => {
      if (p.id === id) {
        return { ...p, quantity: quantity };
      } 
      return p;
    }))
  }

  const [checkedProductList, setCheckedProductList] = useState([]);
  function toggleCheckMark(id) {
    if (checkedProductList.includes(id)) {
      setCheckedProductList(checkedProductList.filter((i) => i !== id));
    } else {
      setCheckedProductList([...checkedProductList, id]);
    }
  }
  console.log(checkedProductList);

  const totalPrice = ProductList.reduce((acc, product) => {
    if (!checkedProductList.includes(product.id)) {
      return acc + (product.quantity * product.price);
    }
    return acc;
  }, 0);

  return (
    <div className="Shoppingcarts">
      <h1>Boodschappen</h1>
      <table>
        {ProductList.filter(
          (product) => !checkedProductList.includes(product.id)
        ).map((product) => (
          <ShoppingCartItem
            checked={false}
            key={product.id}
            product={product}
            removeProduct={removeProductWithId}
            checkedProduct={toggleCheckMark}
            updateQuantity={updateQuantity}
          />
        ))}
        {ProductList.filter((product) =>
          checkedProductList.includes(product.id)
        ).map((product) => (
          <ShoppingCartItem
            checked={true}
            key={product.id}
            product={product}
            removeProduct={removeProductWithId}
            checkedProduct={toggleCheckMark}
            updateQuantity={updateQuantity}
          />
        ))}
        <tfoot>
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
              onClick={() => setProductList([])}
            />
          </td>
        </tfoot>
      </table>
    </div>
  );
};

export default Shoppingcarts;
