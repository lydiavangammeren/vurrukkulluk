import React, { useEffect, useReducer, useState } from 'react'
import DetailsForm from "./DetailsForm"
import IngredientsForm from "./IngredientsForm"
import PreparationForm from "./PreparationForm"
import useAddRecipeContext from '../../hooks/useAddRecipeContext';
import usePostImage from '../../hooks/usePostImage';
import usePostData from '../../hooks/usePostData';
// import ImageForm from './ImageForm';
import useImageResizer from '../../hooks/useImageResizer';
import { useNavigate } from 'react-router-dom';
import validate from 'validate.js';
import { constraints } from '../../constraints/recipeDetails';
import useSlug from '../../hooks/useSlug';

const AddRecipeForm = () => {
  const navigate = useNavigate();
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
    selectedImage,
    setErrors
  } = useAddRecipeContext();
  const [resizedImage, isResized, resize, urlToFile] = useImageResizer()

  const [slug, ready, slugIt] = useSlug();

  const handlePrev = () => setPage(prev => prev - 1)

  const handleNext = () => {
    console.log(selectedImage)
    let validationErrors = validate(data, constraints)
    if(validationErrors) {
      setErrors(validationErrors)
    } else {
      setPage(prev => prev + 1)
    }
  }



  const handleSubmit = async e => {
    e.preventDefault();
    console.log('Handle Submit --> Add Recipe ', e)
    // console.log(JSON.stringify(selectedImage))
    
    try{
      var slugger = await slugIt(data.name);

      const body = {
        "title": data.name,
        "imageId": 1,
        "persons": data.persons,
        // "slug": slugify(data.name, {lower: true, strict: true}),
        "slug": slugger,
        "description": data.description,
        "kitchenTypeId": data.type,
        "kitchenRegionId": data.region,
        "userId": JSON.parse(localStorage.getItem('user')).id,
        "categoryIds": data.categories,
        "ingredients": data.ingredients,
        "preparations": data.preparation
      }

      console.log(body)
    
      postRecipe('/recipes', body)
      resize(selectedImage.src);
    } catch(err){
      console.log('postRecipe Error: ', err)
      alert('You need to be logged in')
    }
    
  }

  // useEffect(() => {
  //   if(recipeLoaded){
  //     resize(selectedImage.src);
  //   }
  // }, [recipeLoaded, recipe])

  // useEffect(() => {
  //   if(isResized){
  //     postImage('/image?type=recipe&id=' + recipe.payLoad, {image: resizedImage})
  //   }
  // }, [isResized, resizedImage])

  useEffect(() => {
    if(recipeLoaded && isResized){
      // console.log(recipe.payLoad)
      // console.log('postImage(selectedImage)', resizedImage)
      postImage('/image?type=recipe&id=' + recipe.payLoad, {image: resizedImage})
    }
  }, [recipeLoaded, isResized])

  useEffect(()=>{
    if(!imageLoaded) return;
    console.log('image status: ', image.status)
    console.log('uploaded image: ', resizedImage)
    navigate(0) // To refetch all recipes, including the one just added.
  }, [imageLoaded])

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
            <button className={`formButton ${submitHide}`} disabled={!canSubmit}>Submit</button>
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
