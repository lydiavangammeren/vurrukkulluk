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
      persons: 4,
      categories: [],
      ingredients: [],
      preparation: []
    });

    const [instructions, setInstructions] = useState('')

    const [selectedImage, setSelectedImage] = useState({file: null, src: null});

    const handleChange = e => {
      const type = e.target.type
      const name = e.target.name
      console.log('type: ' + type)

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
            return { ...ingredient, amount: newQuantity };
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
      // const propertyName = propertyMap[name];
      setData(prevData => ({
        ...prevData,
         ingredients: [...data.ingredients].filter((property) => property.articleId !== value)
      }))
    }
  
    const updatePreparationInstructions = (stepNumber, newInstructions) => {
      setData(prevData => {
        const updatedPreparation = prevData.preparation.map(step => {
          if (step.step == stepNumber) {
            return { ...step, instructions: newInstructions };
          }
          return step;
        });
  
        return { ...prevData, preparation: updatedPreparation };
      });
    };

    const updatePreparationSteps = () => {
      setData(prevData => {
        const updatedPreparation = prevData.preparation.map((step, index) => {
          step.step = index + 1;
          return step;
        });
        console.log('update steps')
        return { ...prevData, preparation: updatedPreparation };
      });
    }

    const removeStep = (e) => {
      const name = e.target.name;
      const value = e.target.value;
      console.log('name: ' + name)
      console.log('value: ' + value)
      // const propertyName = propertyMap[name];
      // setData(prevData => ({
      //   ...prevData,
      //   preparation: [...data.preparation].filter((step) => step.step != value)
      // }))
      removeAndUpdateSteps(value)
      
      // updatePreparationSteps();
    }

    const removeAndUpdateSteps = (index) => {
      // const index = e.target.value
      setData(prevData => {
        // Create a copy of the previous state
        const newData = { ...prevData };
    
        if (index >= 0 && index < newData.preparation.length) {
          newData.preparation.splice(index, 1); // Remove the object at the specified index
          // newData.preparation.filter((step)=> step.step != index)
    
          // Update the "step" property of each object
          for (let i = 0; i < newData.preparation.length; i++) {
            newData.preparation[i].step = i + 1;
          }
        }
    
        return newData; // Return the updated state
      });
    }

    // ===============================================================

    const addItem = (name, value) => {
    
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

    // const changeItem  = (name, value) => {
    
    //   const propertyName = propertyMap[name];
    //   const updatedValue = [...data[propertyName], value];
    
    //   setData((prevData) => ({
    //     ...prevData,
    //     [propertyName]: updatedValue,
    //   }));
    // };

    // const canSubmit = [...Object.values(data)].every(Boolean)
    // const canSubmit = true
    const canSubmit = () => {
      if(![...Object.values(data)].every(Boolean)) return false;
      if(data.ingredients.length > 1 || data.preparation.length < 1) return false;
      return true;
    }

    const disablePrev = page === 0

    const disableNext = page === 2

    const prevHide = page === 0 && "remove_button"

    const nextHide = page === Object.keys(title).length - 1 && "remove_button"

    const submitHide = !canSubmit() && "remove_button"

    return (
      <AddRecipeContext.Provider value={{ title, page, setPage, data, setData, canSubmit, handleChange, disablePrev, 
                                  disableNext, prevHide, nextHide, submitHide, addItem, removeItem, updateIngredientQuantity, 
                                  removeIngredient, selectedImage, setSelectedImage, updatePreparationInstructions, removeStep,
                                  instructions, setInstructions }}>
        {children}
      </AddRecipeContext.Provider>
    )
}

export default AddRecipeContext 