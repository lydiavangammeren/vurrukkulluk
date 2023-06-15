import React from 'react'
import {AddRecipeProvider} from '../../contexts/AddRecipeContext';
import AddRecipeForm from './AddRecipeForm';

const AddRecipePage = () => {

  return (
    <AddRecipeProvider >
      <AddRecipeForm />
    </AddRecipeProvider>
  )
}

export default AddRecipePage
