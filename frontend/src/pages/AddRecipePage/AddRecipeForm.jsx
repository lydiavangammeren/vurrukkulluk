import React, { useReducer, useState } from 'react'
import FormInputs from './FormInputs';
// import DetailsForm from "./DetailsForm";
// import IngredientsForm from "./IngredientsForm";
// import PreparationForm from "./PreparationForm";
// import AddRecipePreparation from '../AddRecipeOLD/AddRecipePreperation';
import { useDatabase } from '../../hooks';
import {AddRecipeProvider} from '../../contexts/AddRecipeContext';
import useAddRecipeContext from '../../hooks/useAddRecipeContext';
import "./AddRecipeForm.css";

const ACTIONS = {

}

function reducer(state, action){
  switch(action.type){
    case '':
      return;
    default:
      return state;
  }
}

// handleChange : https://www.youtube.com/watch?v=FGM6FQmdX8I
const AddRecipeForm = () => {
  const [state, dispatch] = useReducer(reducer, {});
  const [articles, articlesLoaded] = useDatabase('articles');
  // const [data, setData] = useState({
  //   name:'',
  //   description:'',
  //   type: 0,
  //   region: 0,
  //   persons: 0,
  //   categorys: [],
  //   image: 0,
  //   ingredients: [],
  //   preparation: []
  // });
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
    submitHide
  } = useAddRecipeContext();
  
  

  // const handleChange = e => {
  //   // const type = e.target.type;
  //   const name = e.target.name;
  //   const value = e.target.value;

  //   setData(prevData => ({
  //     ...prevData, 
  //     [name]: value
  //   }))
  // }

  const handlePrev = () => setPage(prev => prev - 1)

  const handleNext = () => setPage(prev => prev + 1)

  const handleSubmit = e => {
    e.preventDefault();
    console.log(JSON.stringify(data))
  }

  const renderContent = () => {
    if(articlesLoaded){
      return (
        <div className='addRecipe_form'>
          <h1>Voeg uw recept toe</h1>

        <form className='' onSubmit={handleSubmit}>
          
          <h2>{title[page]}</h2>

          <FormInputs />

          <div className="button_bar">

            <div>
              <button type="submit" className={`formButton`} disabled={!canSubmit}>Submit</button>
            </div>

            <div className=''>
              <button type="button" className={`formButton ${prevHide}`} onClick={handlePrev} disabled={disablePrev}>Prev</button>
              <button type="button" className={`formButton ${nextHide}`} onClick={handleNext} disabled={disableNext}>Next</button>
            </div>

          </div>
          
        </form>
        </div>


        // <div>
          
        //   {/* <form onSubmit={handleSubmit}>
        //     <DetailsForm data={data} handleChange={handleChange}/>
        //     <IngredientsForm articles={articles} />
        //     <PreparationForm />
        //     <button>Voeg toe</button>
        //   </form> */}
        //   <FormInputs />
        // </div>
      )
    }
  }

  return (
    renderContent()
  )
}

export default AddRecipeForm
