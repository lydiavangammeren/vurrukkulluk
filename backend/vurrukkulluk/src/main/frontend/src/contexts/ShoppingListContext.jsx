import { createContext, useContext, useReducer, useCallback, useEffect} from "react"
import api from "../lib/recipeAPI"

export const SL_ACTION = {
  POPULATE_LIST: 'populate-list',
  REFRESH_LIST: "refresh-list",
  ADD_RECIPE: 'add-recipe',
  REMOVE_ITEM: 'remove-item',
  TOGGLE_ITEM: 'toggle-item',
  UPDATE_QUANTITY: 'update-quantity',
  REMOVE_ALL: 'remove-all'
}

const ShoppingListContext = createContext()

export function useShopContext() {
  return useContext(ShoppingListContext);
}

function reducer(state, action) {
  // console.log("action", action);
  // console.log("prevState", state);
  switch (action.type) {
    case SL_ACTION.POPULATE_LIST:
      return { ...state, products: action.payload.products}

    case SL_ACTION.ADD_RECIPE:
      
      return { ...state, products: null,
              //  recipeIds: [...state.recipeIds, action.payload.recipeId],
               recipeIds: {...state.recipeIds, [action.payload.recipeId]:action.payload.persons},
               deletedProductIds: state.deletedProductIds.filter(id => !action.payload.articleIds.includes(id)),
               checkedProductIds: state.checkedProductIds.filter(id => !action.payload.articleIds.includes(id))
            }

    case SL_ACTION.REMOVE_ALL:
      // return { ...state, products: []}
      // return {...state, deletedProductIds: state.products.map(p=> p.article.id)}
      return {...state, deletedProductIds: Object.entries(state.products).map(([key, value]) => Number(key))}

    case SL_ACTION.REMOVE_ITEM:
      // return { ...state, products: state.products.filter((p) => p.article.id !== action.payload.id)}
      return { ...state, deletedProductIds: [...state.deletedProductIds, action.payload.id]}

    case SL_ACTION.UPDATE_QUANTITY:
      return {...state, products: Object.entries(state.products).map(([key, value]) => {
        // console.log('key / value: ', key + ' / ' + value)
        if(key == action.payload.id) {
          // console.log('Update quantity: ', value)
          return [key, Number(action.payload.quantity)];
        } else {
          return [key, Number(value)];

        }
        }).reduce(function(result, item) {
          // console.log("result: ", result)
          // console.log("item: ", item)
          result[item[0]]=item[1]; return result}, {})
      }


    case SL_ACTION.TOGGLE_ITEM:
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

export function ShopContextProvider({children}){

  const [state, localDispatch] = useReducer(reducer, { products: null, recipeIds: {}, checkedProductIds: [], deletedProductIds: [] });
  
  const dispatch = async (action) => {
    // console.log('custom action: ', action)
    switch (action.type) {
      case SL_ACTION.REFRESH_LIST:
        // console.log('state @ refresh: ', state)
      if (state.products == null && state.recipeIds.length !== 0) {
        // console.log("getting products for recipies: ", state.recipeIds);
        // async backend call . then(
        api.post("/cart", {recipeIdsPersonMap: state.recipeIds})
        .then((value) => {
          // console.log('response: ', value)
          localDispatch({type:SL_ACTION.POPULATE_LIST, payload: {products: value.data.articlesToBuy, amounts: value.data.articlesAmount}});
        })
        .catch((err) =>
          console.log('catch: ', err)
        )

        // localDispatch({type:SL_ACTION.POPULATE_LIST, payload: {products:tempProducts}});
         // )
      }
      break;
      default:
        localDispatch(action);
        break;
    }
  }

  useEffect(()=> {
    // console.log('current state: ', state)
  },[state])

  return (
    <ShoppingListContext.Provider value={{...state, dispatch}} >
      {children}
    </ShoppingListContext.Provider>
  )
}
