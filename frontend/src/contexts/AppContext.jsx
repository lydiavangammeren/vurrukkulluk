import { createContext, useContext, useState} from "react"

const AppContext = createContext()

export function useAppContext() {
  return useContext(AppContext);
}

export function ContextProvider({children, recipes}){
  const [searchValue, setSearchValue]= useState('');

  return (
    <AppContext.Provider value={{recipes, searchValue, setSearchValue}} >
      {children}
    </AppContext.Provider>
  )
}

// export default AppContext