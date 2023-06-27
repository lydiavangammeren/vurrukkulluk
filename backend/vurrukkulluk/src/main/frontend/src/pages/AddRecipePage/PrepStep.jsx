import React from 'react'
import useAddRecipeContext from '../../hooks/useAddRecipeContext'

const PrepStep = ({prepstep}) => {

  const {updatePreparationInstructions, removeStep} = useAddRecipeContext();

  const updateInstructions = (e) => {
    const id = e.target.id
    const instructions = e.target.value

    updatePreparationInstructions(id, instructions)
  }

  return (
    <div className='prepstep_list_item'>
      <div className='step_number'>
        {prepstep.step}
      </div>
      <textarea
        name='preparation'
        id={prepstep.step}
        value={prepstep.instructions}
        onChange={updateInstructions}
      />
      <button
        type='button'
        className='remove_step_button'
        name='preparation'
        value={prepstep.step -1}
        onClick={removeStep}
      >Verwijder</button>
    </div>
  )
}

export default PrepStep
