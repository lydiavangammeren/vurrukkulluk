import React, { useEffect, useState } from "react";
import ShoppingCartItem from "./ShoppingCartItem";
import "./ShoppingCart.css";
import api from "../../lib/recipeAPI";
import { RiDeleteBinLine } from "react-icons/ri";
// import { SC_ACTION } from "./ShoppingCartActions";
import { useDatabase } from "../../hooks";

// import useLocalStorage from "../../hooks/useLocalStorage";
import { useShopContext, SL_ACTION } from "../../contexts";

// function reducer(state, action) {
//   switch (action.type) {
//     case SC_ACTION.POPULATE_LIST:
//       return { ...state, products: action.payload.products}

//     case SC_ACTION.REMOVE_ALL:
//       return { ...state, products: []}

//     case SC_ACTION.REMOVE_ITEM:
//       return { ...state, products: state.products.filter((p) => p.id !== action.payload.id)}

//     case SC_ACTION.UPDATE_QUANTITY:
//       return { ...state, products: state.products.map((p) => {
//         if (p.id === action.payload.id) {
//           return { ...p, quantity: action.payload.quantity };
//         } 
//         return p;
//       })}

//     case SC_ACTION.TOGGLE_ITEM:
//       if (state.checkedProductIds.includes(action.payload.id)) {
//         return {...state, checkedProductIds: state.checkedProductIds.filter((i) => i !== action.payload.id)}
//       } else {
//         return { ...state, checkedProductIds: [...state.checkedProductIds, action.payload.id]}
//       }
  

//     default: 
//       console.log("unknown action in reduce for shopping cart: " + action.type);
//       return state
//   }
// }

const ShoppingCart = () => {
  /* video on useReducer: https://www.youtube.com/watch?v=kK_Wqx3RnHk */
  // const [state, dispatch] = useReducer(reducer, { products: [], checkedProductIds: [] });
  const {products, recipeIds, checkedProductIds, deletedProductIds, dispatch} = useShopContext();
  const [articles, articlesLoaded ] = useDatabase('/articles');
  const [allProducts, setAllProducts] = useState(null);
  // const [availableProducts, setAvailableProducts] = useState([]);
  // const [unavailableProducts, setUnvailableProducts] = useState([])
  
  useEffect(() => {
    dispatch({type: SL_ACTION.REFRESH_LIST});
   }, [products, recipeIds, dispatch] )

   useEffect(()=> {
    console.log('Do we have products?: ', products)
    console.log('articlesLoaded: ', articlesLoaded)
    if(!products || !articlesLoaded) return;
    console.log('create array from products object', articles)
    // let fullProducts = Object.keys(products).forEach(function(key, value) {
    //     return {
    //       article: articles.filter((article) => {return article.id == key}),
    //       amount: value
    //     }
    //   })
    // let fullProducts = Object.entries(products).map(([key, value]) => ({article: articles.filter((article) => {return article.id == key}), amount: value}));
    // console.log('fullProducts: ', fullProducts)
    let fullProducts = Object.entries(products).map(([key, value]) => ({article: articles.find((article) => {return article.id == key}), amount: value}));
    console.log('fullProducts: ', fullProducts)
    setAllProducts(fullProducts)
   },[products, articles])

   useEffect(() => {
    console.log('alles: ', allProducts)
    // if(allProducts){
      
    // }
   }, [allProducts])

  const currentProducts = allProducts ? allProducts.filter(
    (product)=> !deletedProductIds.includes(product.article.id) &&
    product.article.available
  ) : [];

  const otherProducts = allProducts ? allProducts.filter(
    (product)=> !deletedProductIds.includes(product.article.id) &&
    !product.article.available
  ) : [];
  
  // console.log('current products: ', currentProducts)
  const uncheckedProducts = currentProducts.filter(
    (product) => !checkedProductIds.includes(product.article.id))

  const checkedProducts = currentProducts.filter(
    (product) => checkedProductIds.includes(product.article.id))
  
  const uncheckedUnavailable = otherProducts.filter(
    (product) => !checkedProductIds.includes(product.article.id))

  const checkedUnavailable = otherProducts.filter(
    (product) => checkedProductIds.includes(product.article.id))

  const totalPrice = uncheckedProducts.reduce((acc, product) => {
      return acc + ((product.amount * product.article.price)/100);
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
              key={product.article.id}
              product={product}
              dispatch={dispatch}
            />
          ))}
          {checkedProducts.map((product) => (
            <ShoppingCartItem
              checked={true}
              key={product.article.id}
              product={product}
              dispatch={dispatch}
            />
          ))}
        </tbody>
        <tfoot>
          <tr>
            <td colSpan="3">
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
                onClick={() => dispatch({type: SL_ACTION.REMOVE_ALL})}
              />
            </td>
          </tr>
        </tfoot>
      </table>

      {

      <table>
        <thead>
          <tr>
            <th colSpan="6">
              <h1>Uit andere winkel</h1>
            </th>
          </tr>
        </thead>
        
        <tbody>
          {checkedUnavailable.map((product) => (
            <ShoppingCartItem
              checked={false}
              key={product.article.id}
              product={product}
              dispatch={dispatch}
            />
          ))}
          {uncheckedUnavailable.map((product) => (
            <ShoppingCartItem
              checked={true}
              key={product.article.id}
              product={product}
              dispatch={dispatch}
            />
          ))}
        </tbody>
        <tfoot>

        </tfoot>
      </table>
      }
    </div>
  );
};

export default ShoppingCart;
