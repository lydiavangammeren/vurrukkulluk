import { createContext, useContext, useEffect, useState} from "react"

const AppContext = createContext()

export function useAppContext() {
  return useContext(AppContext);
}

export function ContextProvider({children, recipes}){
  const [bannerImages, setBannerImages] = useState([]);
  const [searchValue, setSearchValue]= useState('');

  // useEffect(() => {
  //   console.log('Set banner Images')
  // }, [bannerImages])

  return (
    <AppContext.Provider value={{recipes, searchValue, setSearchValue, bannerImages, setBannerImages}} >
      {children}
    </AppContext.Provider>
  )
}

// export default AppContext