import { createContext, useContext, useReducer, useCallback} from "react"

export const SL_ACTION = {
  POPULATE_LIST: 'populate-list',
  REFRESH_LIST: "refresh-list",
  ADD_RECIPE: 'add-recipe',
  REMOVE_ITEM: 'remove-item',
  TOGGLE_ITEM: 'toggle-item',
  UPDATE_QUANTITY: 'update-quality',
  REMOVE_ALL: 'remove-all'
}

const ShoppingListContext = createContext()

export function useShopContext() {
  return useContext(ShoppingListContext);
}
const tempProducts = [
  {
    "article": {
      "name": "Vegan Burger Bun",
      "imageId": 2,
      "description": "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque eu metus sem. Sed lobortis tempor arcu. Nulla id nulla in nibh dictum feugiat. Donec sed accumsan est, at accumsan velit.",
      "price": 3,
      "unit": "broodje",
      "id": 1
    },
    "amount": 1
  },
  {
    "article": {
      "name": "Vegan Burger",
      "imageId": 3,
      "description": "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque eu metus sem. Sed lobortis tempor arcu. Nulla id nulla in nibh dictum feugiat. Donec sed accumsan est, at accumsan velit.",
      "price": 1,
      "unit": "gram",
      "id": 2
    },
    "amount": 320
  },
  {
    "article": {
      "name": "Vegan Burger Sauce",
      "imageId": 4,
      "description": "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque eu metus sem. Sed lobortis tempor arcu. Nulla id nulla in nibh dictum feugiat. Donec sed accumsan est, at accumsan velit.",
      "price": 1,
      "unit": "ml",
      "id": 3
    },
    "amount": 30
  },
  {
    "article": {
      "name": "Avocado",
      "imageId": 8,
      "description": "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque eu metus sem. Sed lobortis tempor arcu. Nulla id nulla in nibh dictum feugiat. Donec sed accumsan est, at accumsan velit.",
      "price": 2,
      "unit": "stuks",
      "id": 4
    },
    "amount": 1
  }
]


function reducer(state, action) {
  console.log("action", action);
  console.log("prevState", state);
  switch (action.type) {
    case SL_ACTION.POPULATE_LIST:
      return { ...state, products: action.payload.products}

    case SL_ACTION.REFRESH_LIST:
      if (state.products.length === 0 && state.recepiesIds.length !== 0) {
        console.log("getting products for recipies: ", state.recepiesIds);
        // async backend call . then(
         return {...state, products:tempProducts}
         // )
      }
      return state;

    case SL_ACTION.ADD_RECIPE:
      return { ...state, products: [],
               recepiesIds: [...state.recepiesIds, action.payload.recipeId],
               deletedProductIds: state.deletedProductIds.filter(id => !action.payload.articleIds.includes(id)),
               checkedProductIds: state.checkedProductIds.filter(id => !action.payload.articleIds.includes(id))
            }

    case SL_ACTION.REMOVE_ALL:
      // return { ...state, products: []}
      return {...state, deletedProductIds: state.products.map(p=> p.article.id)}

    case SL_ACTION.REMOVE_ITEM:
      // return { ...state, products: state.products.filter((p) => p.article.id !== action.payload.id)}
      return { ...state, deletedProductIds: [...state.deletedProductIds, action.payload.id]}

    case SL_ACTION.UPDATE_QUANTITY:
      return { ...state, products: state.products.map((p) => {
        if (p.article.id === action.payload.id) {
          return { ...p, quantity: action.payload.quantity };
        } 
        return p;
      })}

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

  const [state, localDispatch] = useReducer(reducer, { products: [], recepiesIds: [], checkedProductIds: [], deletedProductIds: [] });
  
  const dispatch = useCallback(async (action) => {
    console.log('custom action: ', action)
    switch (action.type) {
      case SL_ACTION.REFRESH_LIST:
        console.log('state: ', state)
      if (state.products.length === 0 && state.recepiesIds.length !== 0) {
        console.log("getting products for recipies: ", state.recepiesIds);
        // async backend call . then(
        localDispatch({type:SL_ACTION.POPULATE_LIST, payload: {products:tempProducts}});
         // )
      }
      break;
      default:
        localDispatch(action);
        break;
    }
  },[])
  
  return (
    <ShoppingListContext.Provider value={{...state, dispatch}} >
      {children}
    </ShoppingListContext.Provider>
  )
}
