import React, { useState } from "react";
import ShoppingcartContent from "./ShoppingcartContent";
import "./Shoppingcarts.css";


const Shoppingcarts = () => {
  const products = [
    {
      id: "1",
      image: "hamb.jpg",
      title: "Vegan Burger Bun",
      desc: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque eu metus sem. Sed lobortis tempor arcu. Nulla id nulla in nibh dictum feugiat. Donec sed accumsan est, at accumsan velit.",
      quantity: "4 Broodje",
      price: 6.5,
    },
    {
      id: "2",
      image: "VeganBurgerI.jpg",
      title: "Vegan Burger",
      desc: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque eu metus sem. Sed lobortis tempor arcu. Nulla id nulla in nibh dictum feugiat. Donec sed accumsan est, at accumsan velit.",
      quantity: "6 stuks",
      price: 6.5,
    },
    {
      id: "3",
      image: "VeganSauce.jpg",
      title: "Vegan Burger Sauce",
      desc: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque eu metus sem. Sed lobortis tempor arcu. Nulla id nulla in nibh dictum feugiat. Donec sed accumsan est, at accumsan velit.",
      quantity: "1 stuk",
      price: 6.5,
    },
    {
      id: "4",
      image: "avocado.jpg",
      title: "Avocado",
      desc: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque eu metus sem. Sed lobortis tempor arcu. Nulla id nulla in nibh dictum feugiat. Donec sed accumsan est, at accumsan velit.",
      quantity: "2 stuks",
      price: 6.5,
    },
  ];

  const [ProductList, setProductList] = useState(products);
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
      <h2>Totaal</h2>
    </div>
  );
};

export default Shoppingcarts;
