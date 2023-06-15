import { createContext, useState, useEffect } from "react"

const AddRecipeContext = createContext({})

export const AddRecipeProvider = ({ children }) => {

    const title = {
      0: 'Details',
      1: 'Ingredients',
      2: 'Preparation'
    }

    const [page, setPage] = useState(0)

    const [data, setData] = useState({
      name:'',
      imageId: 0,
      slug: '',
      description:'',
      type: 0,
      region: 0,
      persons: 0,
      categories: [],
      // recipeImage: 0,
      ingredients: [],
      preparation: []
    });

    const handleChange = e => {
      const type = e.target.type

      const name = e.target.name

      const value = type === "checkbox"
        ? e.target.checked
        : e.target.value

      setData(prevData => ({
        ...prevData,
        [name]: value
      }))
    }

    const propertyMap = {
      categories: 'categories',
      ingredients: 'ingredients',
      preparation: 'preparation',
    };

    const addItem = (name, value) => {
      // const name = e.target.name;
      // const value = e.target.value;
    
      const propertyName = propertyMap[name];
      const updatedValue = [...data[propertyName], value];
    
      setData((prevData) => ({
        ...prevData,
        [propertyName]: updatedValue,
      }));
    };

    const removeItem = (e) => {
      const name = e.target.name;
      const value = e.target.value;
      const propertyName = propertyMap[name];
      setData(prevData => ({
        ...prevData,
         [propertyName]: [...data[propertyName]].filter((id) => id !== value)
      }))
    }

    const canSubmit = [...Object.values(data)].every(Boolean) && page === Object.keys(title).length - 1

    const disablePrev = page === 0

    const disableNext = page === 2

    const prevHide = page === 0 && "remove_button"

    const nextHide = page === Object.keys(title).length - 1 && "remove_button"

    const submitHide = page !== Object.keys(title).length - 1 && "remove_button"

    return (
      <AddRecipeContext.Provider value={{ title, page, setPage, data, setData, canSubmit, handleChange, disablePrev, 
                                  disableNext, prevHide, nextHide, submitHide, addItem, removeItem }}>
        {children}
      </AddRecipeContext.Provider>
    )
}

export default AddRecipeContext 