import React, { useRef, useState } from 'react'
import useAddRecipeContext from '../../hooks/useAddRecipeContext'

const PreparationForm = () => {

  const { data, handleChange, addItem } = useAddRecipeContext();
  const [ nextStep, setNextStep] = useState(1);
  // const instructions = useRef();
  const [ instructions, setInstructions ] = useState('');

  const handleAdd = e => {
    const name = e.target.name
    const value = {
      step: nextStep,
      instructions: instructions
    }
    addItem(name, value)
    // instructions.current = '';
    setInstructions('');
    setNextStep(prev => prev + 1);
  }



  return (
    <div>
      <div className='add_preparation'>
        <div className='add_preparation_step'>
          {nextStep}
        </div>

        <textarea
          name=''
          placeholder=''
          value={instructions}
          onChange={(e) => setInstructions(e.target.value)}
        />
        <button
         name='preparation'
         onClick={handleAdd}
        >Voeg stap toe</button>
      </div>
      <div className='prepstep_list'>
        {data.preparation &&
        data.preparation.map((prepstep)=>{
          return (
            <div className='prepstep_list_item'>
              <h2>{prepstep.step}</h2>
              <p>{prepstep.instructions}</p>
            </div>
          )
        })

        }
      </div>
    </div>
  )
}

export default PreparationForm
