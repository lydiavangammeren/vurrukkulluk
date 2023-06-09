import { createContext, useContext, useEffect, useState} from "react"

const AppContext = createContext()

export function useAppContext() {
  return useContext(AppContext);
}

export function ContextProvider({children, recipes, images}){
  // const [bannerImages, setBannerImages] = useState(images);
  const bannerImages = images;
  const [detailImage, setDetailImage] = useState();
  const [searchValue, setSearchValue]= useState('');
  const baseUrl = "/images/";
  // const baseUrl = "http://localhost:8080/image/";

  // console.log('Context render');
  // useEffect(() => {
  //   console.log('Set banner Images')
  // }, [])

  return (
    <AppContext.Provider value={{recipes, searchValue, setSearchValue, bannerImages, detailImage, setDetailImage, baseUrl}} >
      {children}
    </AppContext.Provider>
  )
}

// export default AppContext