import { createContext, useContext, useState} from "react"

const AppContext = createContext()

export function useAppContext() {
  return useContext(AppContext);
}

export function ContextProvider({children, recipes, bannerImages}){
  const [detailImage, setDetailImage] = useState();
  const [searchValue, setSearchValue]= useState('');
  const baseUrl = "/images/";
  // const baseUrl = "http://localhost:8080/image/";

  return (
    <AppContext.Provider value={{recipes, searchValue, setSearchValue, bannerImages, detailImage, setDetailImage, baseUrl}} >
      {children}
    </AppContext.Provider>
  )
}

// export default AppContext