import React, { useReducer, useState } from 'react'
import AddRecipeDetails from "./AddRecipeDetails";
import AddRecipeIngredients from "./AddRecipeIngredients";
import AddRecipePreparation from "./AddRecipePreparation";
// import AddRecipePreparation from '../AddRecipeOLD/AddRecipePreperation';
import { useDatabase } from '../../hooks';

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


const AddRecipePage = () => {
  const [state, dispatch] = useReducer(reducer, {});
  const [articles, articlesLoaded] = useDatabase('articles');
  const [data, setData] = useState({
    name:'',
    description:'',
    type: 0,
    region: 0,
    persons: 0,
    categorys: [],
    image: 0,
    ingredients: [],
    preparation: []
  });
  
  const handleSubmit = e => {
    e.preventDefault();
    console.log(JSON.stringify(data))
  }

  const handleChange = e => {
    // const type = e.target.type;
    const name = e.target.name;
    const value = e.target.value;

    setData(prevData => ({
      ...prevData, 
      [name]: value
    }))
  }

  const renderContent = () => {
    if(articlesLoaded){
      return (
        <div>
          <form onSubmit={handleSubmit}>
            <AddRecipeDetails data={data} handleChange={handleChange}/>
            <AddRecipeIngredients articles={articles} />
            <AddRecipePreparation />
            <button>Voeg toe</button>
          </form>
        </div>
      )
    }
  }

  return (
    renderContent()
  )
}

export default AddRecipePage
