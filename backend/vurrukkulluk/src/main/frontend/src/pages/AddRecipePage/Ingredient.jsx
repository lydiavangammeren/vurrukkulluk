import React from 'react'
import useAddRecipeContext from '../../hooks/useAddRecipeContext'
import { useAppContext } from '../../contexts';
import { RiDeleteBinLine } from "react-icons/ri";

const Ingredient = ({articleunit, ingredient}) => {

  const { baseUrl } = useAppContext();
  const { updateIngredientQuantity, removeItem, removeIngredient } = useAddRecipeContext();
  const article = articleunit.article;
  const unit = articleunit.unit.name;

  const updateQuantity = (e) => {
    const id = e.target.id
    const quantity = e.target.value

    updateIngredientQuantity(id, quantity)
  }

  return (
    <div className='ingredient_item' key={ingredient.articleId}>
        <div className="ingredient_item_image">
          <img src={baseUrl + article?.imageId} />
        </div>

        <div className="ingredient_item_content">
          <h3 className="pretty greenFont">{article?.name}</h3>

          <div className="ingredient_item_quantity">
            {/* <div> */}
              <label htmlFor='amount'>Hoeveelheid: </label>
              <input type='number'
                value={ingredient.amount}
                name='amount'
                id={article?.id}
                onChange={updateQuantity}
              />
              <span>{unit}</span>
            {/* </div> */}
          </div>
        </div>
        <div className='ingredient_item_buttons'>
          <button type='button' onClick={removeIngredient} name='ingredients' value={article?.id} className='delete_button'>
            {/* Verwijder */}
            <RiDeleteBinLine color='red' size={24}/>
          </button>
        </div>
    </div>


  )
}

export default Ingredient
