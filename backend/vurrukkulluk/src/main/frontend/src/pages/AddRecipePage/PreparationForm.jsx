import React, { useEffect, useRef, useState } from 'react'
import useAddRecipeContext from '../../hooks/useAddRecipeContext'
import PrepStep from './PrepStep';

const PreparationForm = () => {

  const { data, setData, handleChange, addItem, instructions, setInstructions} = useAddRecipeContext();
  const [ nextStep, setNextStep] = useState(1);
  // const instructionsRef = useRef();
  // const [ instructions, setInstructions ] = useState('');
  // const [ canEdit, setCanEdit ] = useState(false);

  const handleAdd = e => {
    const name = 'preparation';
    const value = {
      step: data.preparation.length + 1,
      instructions: instructions
    }
    addItem(name, value)
    setInstructions('');
  }

  const handleEnter = (e) => {
    if(e.keyCode != 13) return;
    handleAdd(e)
  }

  // useEffect(() => {
  //   instructionsRef.focus();
  // }, [])

  
  return (
    <div>
      
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
      <div className='add_preparation'>
        <div className='step_number'>
          {data.preparation.length + 1}
        </div>

        <textarea
          name='instructions'
          placeholder=''
          value={instructions}
          onChange={e => setInstructions(e.target.value)}
          autoFocus
          onKeyDown={handleEnter}
        />
        <button
          type='button'
          className='add_step_button'
          name='preparation'
          onClick={handleAdd}
        >Voeg stap toe</button>
      </div>
    </div>
  )
}

export default PreparationForm
