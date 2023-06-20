import React from 'react'
import useAddRecipeContext from '../../hooks/useAddRecipeContext'
import { useAppContext } from '../../contexts';
import { RiDeleteBinLine } from "react-icons/ri";

const Ingredient = ({article, ingredient}) => {

  const { baseUrl } = useAppContext();
  const { updateIngredientQuantity, removeItem, removeIngredient } = useAddRecipeContext();

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
              <label htmlFor='quantity'>Hoeveelheid:</label>
              <input type='number'
                value={ingredient.quantity}
                name='quantity'
                id={article?.id}
                onChange={updateQuantity}
              />
              <span>{article?.unit}</span>
            {/* </div> */}
          </div>
        </div>
        <div className='ingredient_item_buttons'>
          <button type='button' onClick={removeIngredient} name='ingredients' value={article?.id} className='delete_button'>
            Verwijder
            {/* <RiDeleteBinLine color='red' size={24}/> */}
          </button>
        </div>
    </div>


  )
}

export default Ingredient
