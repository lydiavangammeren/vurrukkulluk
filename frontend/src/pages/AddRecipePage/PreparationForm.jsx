import React, { useRef, useState } from 'react'
import useAddRecipeContext from '../../hooks/useAddRecipeContext'
import PrepStep from './PrepStep';

const PreparationForm = () => {

  const { data, handleChange, addItem} = useAddRecipeContext();
  const [ nextStep, setNextStep] = useState(1);
  // const instructions = useRef();
  const [ instructions, setInstructions ] = useState('');
  // const [ canEdit, setCanEdit ] = useState(false);

  const handleAdd = e => {
    const name = e.target.name
    const value = {
      // step: nextStep,
      step: data.preparation.length + 1,
      instructions: instructions
    }
    addItem(name, value)
    // instructions.current = '';
    setInstructions('');
    // setNextStep(prev => prev + 1);
  }

  
  return (
    <div>
      <div className='add_preparation'>
        <div className='step_number'>
          {/* {nextStep} */}
          {data.preparation.length + 1}
        </div>

        <textarea
          name=''
          placeholder=''
          value={instructions}
          onChange={(e) => setInstructions(e.target.value)}
        />
        <button
          type='button'
          className='add_step_button'
          name='preparation'
          onClick={handleAdd}
        >Voeg stap toe</button>
      </div>
      <h2>Toegevoegde stappen: </h2>
      <div className='prepstep_list'>
        {data.preparation &&
        data.preparation.map((prepstep)=>{
          return (
            <PrepStep prepstep={prepstep} key={prepstep.step}/>
          )
        })

        }
      </div>
    </div>
  )
}

export default PreparationForm
