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
      name: '',
      description: '',
      type: 0,
      region: 0,
      persons: 0,
      categories: [],
      ingredients: [
        // { id: 1, quantity: 4 },
        // { id: 2, quantity: 250 },
        // { id: 3, quantity: 100 }
      ],
      preparation: [
        // { step: 1, instructions: "Lorem ipsum ..." },
        // { step: 2, instructions: "Lorem ipsum ..." },
        // { step: 3, instructions: "Lorem ipsum ..." }
      ]
    });

    const handleChange = e => {
      const type = e.target.type

      const name = e.target.name

      // const value = type === "checkbox"
      //   ? e.target.checked
      //   : e.target.value

      const value = e.target.value

      if(name === "")
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

    // const updateProperty = (e, ) => {
    //   setData(prevData => {
    //     const updatedNestedArray = prevData.nestedArray.map(item => {
    //       if (item.id === id) {
    //         return { ...item, property: newPropertyValue };
    //       }
    //       return item;
    //     });
  
    //     return { ...prevData, nestedArray: updatedNestedArray };
    //   });
    // };

    // ==================== CHAT GPT =================================

    const updateIngredientQuantity = (ingredientId, newQuantity) => {
      setData(prevData => {
        const updatedIngredients = prevData.ingredients.map(ingredient => {
          if (ingredient.articleId == ingredientId) {
            return { ...ingredient, quantity: newQuantity };
          }
          return ingredient;
        });
  
        return { ...prevData, ingredients: updatedIngredients };
      });
    };

    const removeIngredient = (e) => {
      const name = e.target.name;
      const value = e.target.value;
      console.log('name: ' + name)
      console.log('value: ' + value)
      const propertyName = propertyMap[name];
      setData(prevData => ({
        ...prevData,
         ingredients: [...data.ingredients].filter((property) => property.articleId !== value)
      }))
    }
  
    const updatePreparationInstructions = (stepNumber, newInstructions) => {
      setData(prevData => {
        const updatedPreparation = prevData.preparation.map(step => {
          if (step.step === stepNumber) {
            return { ...step, instructions: newInstructions };
          }
          return step;
        });
  
        return { ...prevData, preparation: updatedPreparation };
      });
    };

    // ===============================================================

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

    const changeItem  = (name, value) => {
    
      const propertyName = propertyMap[name];
      const updatedValue = [...data[propertyName], value];
    
      setData((prevData) => ({
        ...prevData,
        [propertyName]: updatedValue,
      }));
    };

    const canSubmit = [...Object.values(data)].every(Boolean) && page === Object.keys(title).length - 1

    const disablePrev = page === 0

    const disableNext = page === 2

    const prevHide = page === 0 && "remove_button"

    const nextHide = page === Object.keys(title).length - 1 && "remove_button"

    const submitHide = page !== Object.keys(title).length - 1 && "remove_button"

    return (
      <AddRecipeContext.Provider value={{ title, page, setPage, data, setData, canSubmit, handleChange, disablePrev, 
                                  disableNext, prevHide, nextHide, submitHide, addItem, removeItem, updateIngredientQuantity, removeIngredient }}>
        {children}
      </AddRecipeContext.Provider>
    )
}

export default AddRecipeContext 