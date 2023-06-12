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
      description:'',
      type: 0,
      region: 0,
      persons: 0,
      categories: [],
      recipeImage: 0,
      ingredients: [],
      preparation: [],
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

    const addCategory = e => {

      const value = e.target.value
      console.log('Add Category: ' + value)

      if(!data.categories.find(category => category.id === value)){
        setData(prevData => ({
          ...prevData,
           categories: [...data.categories, value]
        }))
      }
    }

    // const {
    //     billAddress2,
    //     sameAsBilling,
    //     shipAddress2,
    //     optInNews,
    //     ...requiredInputs } = data

    const canSubmit = [...Object.values(data)].every(Boolean) && page === Object.keys(title).length - 1

    // const canNextPage1 = Object.keys(data)
    //     .filter(key => key.startsWith('bill') && key !== 'billAddress2')
    //     .map(key => data[key])
    //     .every(Boolean)

    // const canNextPage2 = Object.keys(data)
    //     .filter(key => key.startsWith('ship') && key !== 'shipAddress2')
    //     .map(key => data[key])
    //     .every(Boolean)

    const disablePrev = page === 0

    const disableNext = page === 2
    // (page === Object.keys(title).length - 1)
    // || (page === 0 && !canNextPage1)
    // || (page === 1 && !canNextPage2)

    const prevHide = page === 0 && "remove_button"

    const nextHide = page === Object.keys(title).length - 1 && "remove_button"

    const submitHide = page !== Object.keys(title).length - 1 && "remove_button"

    return (
      <AddRecipeContext.Provider value={{ title, page, setPage, data, setData, canSubmit, handleChange, disablePrev, 
                                  disableNext, prevHide, nextHide, submitHide, addCategory }}>
        {children}
      </AddRecipeContext.Provider>
    )
}

export default AddRecipeContext 