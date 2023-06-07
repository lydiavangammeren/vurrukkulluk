import { createContext, useContext, useEffect, useState} from "react"

const AppContext = createContext()

export function useAppContext() {
  return useContext(AppContext);
}

export function ContextProvider({children, recipes, images}){
  // const [bannerImages, setBannerImages] = useState(images);
  const [detailImage, setDetailImage] = useState([]);
  const [searchValue, setSearchValue]= useState('');

  // console.log('Context render');
  // useEffect(() => {
  //   console.log('Set banner Images')
  // }, [])

  return (
    <AppContext.Provider value={{recipes, searchValue, setSearchValue, detailImage, setDetailImage}} >
      {children}
    </AppContext.Provider>
  )
}

// export default AppContext