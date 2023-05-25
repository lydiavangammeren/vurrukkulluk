import React, { useState, useEffect } from "react";
import React, { useState, useEffect } from "react";
import ShoppingcartContent from "./ShoppingcartContent";
import "./Shoppingcarts.css";
import api from "../../lib/recipeAPI";
import api from "../../lib/recipeAPI";

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
      return acc + product.price;
    }
    return acc;
  }, 0);

  return (
    <div className="Shoppingcarts">
      <h1>Boodschappen</h1>
      {ProductList.filter(
        (product) => !checkedProductList.includes(product.id)
      ).map((product) => (
        <ShoppingcartContent
          checked={false}
          key={product.id}
          id={product.id}
          image={product.image}
          title={product.title}
          desc={product.desc}
          quantity={product.quantity}
          price={product.price}
          removeProduct={removeProductWithId}
          checkedProduct={toggleCheckMark}
        />
      ))}
      {ProductList.filter((product) =>
        checkedProductList.includes(product.id)
      ).map((product) => (
        <ShoppingcartContent
          checked={true}
          key={product.id}
          id={product.id}
          image={product.image}
          title={product.title}
          desc={product.desc}
          quantity={product.quantity}
          price={product.price}
          removeProduct={removeProductWithId}
          checkedProduct={toggleCheckMark}
        />
      ))}
      <h2>Total: {totalPrice}</h2>
    </div>
  );
};

export default Shoppingcarts;
