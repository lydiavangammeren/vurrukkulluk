import React, { useEffect, useState } from "react";
import ShoppingCartItem from "./ShoppingCartItem";
import "./ShoppingCart.css";
import api from "../../lib/recipeAPI";
import { RiDeleteBinLine } from "react-icons/ri";
// import { SC_ACTION } from "./ShoppingCartActions";
import { useDatabase } from "../../hooks";

// import useLocalStorage from "../../hooks/useLocalStorage";
import { useShopContext, SL_ACTION } from "../../contexts";

const ShoppingCart = () => {
  /* video on useReducer: https://www.youtube.com/watch?v=kK_Wqx3RnHk */
  // const [state, dispatch] = useReducer(reducer, { products: [], checkedProductIds: [] });
  const {products, recipeIds, checkedProductIds, deletedProductIds, dispatch} = useShopContext();
  const [articles, articlesLoaded ] = useDatabase('/articles');
  const [articleunits, articleunitsLoaded ] = useDatabase('/articleunits');
  const [allProducts, setAllProducts] = useState(null);
  // const [availableProducts, setAvailableProducts] = useState([]);
  // const [unavailableProducts, setUnvailableProducts] = useState([])
  
  const refresh = () => {
    dispatch({type: SL_ACTION.REFRESH_LIST});
  }

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
    let fullProducts = Object.entries(products).map(([key, value]) => ({article: articleunits?.find((article) => {return article.article.id == key}), amount: value}));
    // let fullProducts = Object.entries(products).map(([key, value]) => ({article: articles.find((article) => {return article.id == key}), amount: value}));
    console.log('fullProducts: ', fullProducts)
    setAllProducts(fullProducts)
   },[products, articles])

   useEffect(() => {
    console.log('alles: ', allProducts)
    // if(allProducts){
      
    // }
   }, [allProducts])

  const currentProducts = allProducts ? allProducts.filter(
    (product)=> !deletedProductIds.includes(product.article.article.id) &&
    product.article.article.available
  ) : [];

  const otherProducts = allProducts ? allProducts.filter(
    (product)=> !deletedProductIds.includes(product.article.article.id) &&
    !product.article.article.available
  ) : [];
  
  // console.log('current products: ', currentProducts)
  const uncheckedProducts = currentProducts.filter(
    (product) => !checkedProductIds.includes(product.article.article.id))

  const checkedProducts = currentProducts.filter(
    (product) => checkedProductIds.includes(product.article.article.id))
  
  const uncheckedUnavailable = otherProducts.filter(
    (product) => checkedProductIds.includes(product.article.article.id))

  const checkedUnavailable = otherProducts.filter(
    (product) => !checkedProductIds.includes(product.article.article.id))

  const totalPrice = uncheckedProducts.reduce((acc, product) => {
      return acc + ((product.amount * product.article.article.price)/100);
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
