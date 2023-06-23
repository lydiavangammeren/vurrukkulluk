import React, { useReducer, useState } from 'react'
import DetailsForm from "./DetailsForm"
import IngredientsForm from "./IngredientsForm"
import PreparationForm from "./PreparationForm"
import useAddRecipeContext from '../../hooks/useAddRecipeContext';
import usePostImage from '../../hooks/usePostImage';
import usePostData from '../../hooks/usePostData';

const AddRecipeForm = () => {
  
  const [image, imageLoaded, postImage] = usePostImage();
  // const [recipe, recipeLoaded, postRecipe] = usePostData();
  const {
    page,
    setPage,
    data,
    title,
    canSubmit,
    disablePrev,
    disableNext,
    prevHide,
    nextHide,
    submitHide,
    selectedImage
  } = useAddRecipeContext();

  const handlePrev = () => setPage(prev => prev - 1)

  const handleNext = () => setPage(prev => prev + 1)

  const handleSubmit = e => {
    e.preventDefault();
    console.log('Handle Submit --> Add Recipe')
    console.log(JSON.stringify(selectedImage))

    // postImage('/image', {image: selectedImage})
  }

  const display = {
    0: <DetailsForm />,
    1: <IngredientsForm />,
    2: <PreparationForm />
  }

  const renderContent = () => {
    // if(articlesLoaded){
      return (
        <div className='addRecipe_form'>
          <h1>Voeg uw recept toe</h1>

        <form className='' onSubmit={handleSubmit}>
          
          <h2>{title[page]}</h2>

          <div className="form_inputs">
            {display[page]}
          </div>

          <div className="button_bar">
            <div>
              <button type="submit" className={`formButton`} disabled={!canSubmit}>Submit</button>
            </div>

            <div className=''>
              <button type="button" className={`formButton ${prevHide}`} onClick={handlePrev} disabled={disablePrev}>Terug</button>
              <button type="button" className={`formButton ${nextHide}`} onClick={handleNext} disabled={disableNext}>Volgende</button>
            </div>
          </div>
          
        </form>
        </div>
      )
    // }
  }

  return (
    renderContent()
  )
}

export default AddRecipeForm
