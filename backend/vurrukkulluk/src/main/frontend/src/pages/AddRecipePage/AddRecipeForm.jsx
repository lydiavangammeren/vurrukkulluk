import React, { useEffect, useReducer, useState } from 'react'
import DetailsForm from "./DetailsForm"
import IngredientsForm from "./IngredientsForm"
import PreparationForm from "./PreparationForm"
import useAddRecipeContext from '../../hooks/useAddRecipeContext';
import usePostImage from '../../hooks/usePostImage';
import usePostData from '../../hooks/usePostData';
import ImageForm from './ImageForm';

const AddRecipeForm = () => {
  var slugify = require('slugify');
  const [image, imageLoaded, postImage] = usePostImage();
  const [recipe, recipeLoaded, postRecipe] = usePostData();
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
    // console.log(JSON.stringify(selectedImage))

    const body = {
      "title": data.name,
      "imageId": 1,
      "persons": data.persons,
      "slug": slugify(data.name, {lower: true, strict: true}),
      "description": data.description,
      "kitchenTypeId": data.type,
      "kitchenRegionId": data.region,
      "userId": JSON.parse(localStorage.getItem('user')).id,
      "categoryIds": data.categories,
      "ingredients": data.ingredients,
      "preparations": data.preparation
    }

    // console.log(body)
    postRecipe('/recipes', body)

    // console.log('image upload: ', selectedImage.file)
    // postImage('/image?type=recipe&id=4', {image: selectedImage.file})
  }

  useEffect(() => {
    if(recipeLoaded){
      console.log(recipe.payLoad)
      // console.log('postImage(selectedImage)')
      postImage('/image?type=recipe&id=' + recipe.payLoad, {image: selectedImage.file})
    }
  }, [recipeLoaded, recipe, imageLoaded])

  const display = {
    0: <DetailsForm />,
    1: <ImageForm />,
    2: <IngredientsForm />,
    3: <PreparationForm />
  }

  const renderContent = () => {
    // if(articlesLoaded){
      return (
        <div className='addRecipe_form'>
          <h1>Voeg uw recept toe</h1>

        <form className='' onSubmit={handleSubmit}>
          <div className='title_button_bar'>
            <div>
              <h2>{title[page]}</h2>
            </div>
            <div className='next_prev'>
              <button type="button" className={`formButton ${prevHide}`} onClick={handlePrev} disabled={disablePrev}>Terug</button>
              <button type="button" className={`formButton ${nextHide}`} onClick={handleNext} disabled={disableNext}>Volgende</button>
            </div>
          </div>

          <div className="form_inputs">
            {display[page]}
          </div>

          <div className="button_bar">
            <button type="submit" className={`formButton ${submitHide}`} disabled={!canSubmit}>Submit</button>
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
