import { createContext, useContext, useState} from "react"

const AppContext = createContext()

export function useAppContext() {
  return useContext(AppContext);
}

export function ContextProvider({children, recipes, bannerImages, keepRecipeCache}){
  const [detailImage, setDetailImage] = useState();
  const [exampleImage, setExampleImage] = useState(null);
  const [searchValue, setSearchValue]= useState('');
  // const [user, setUser] = useState(null);
  // const baseUrl = "/images/";
  const baseUrl = "http://localhost:8080/api/image/";

  return (
    <AppContext.Provider value={{recipes, searchValue, setSearchValue, bannerImages, detailImage, setDetailImage,
      baseUrl, exampleImage, setExampleImage, keepRecipeCache}} >
      {children}
    </AppContext.Provider>
  )
}

// export default AppContext
