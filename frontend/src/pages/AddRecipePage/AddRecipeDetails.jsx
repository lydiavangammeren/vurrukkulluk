import React from 'react'
import { useDatabase } from '../../hooks';
import { useAppContext } from '../../contexts';
import './AddRecipeDetails.css';

const AddRecipeDetails = ({data, handleChange}) => {
  const [types, typesLoaded ] = useDatabase('/kitchentypes');
  const [regions, regionsLoaded] = useDatabase('/kitchenregions');
  const [categorys, categorysLoaded] = useDatabase('/kitchencategorys');
  const { recipes } = useAppContext();

  const renderContent = () => {
    if(typesLoaded && regionsLoaded && categorysLoaded){
      return (
        <div className='addDetails'>
              <label htmlFor='name'>Naam</label>
              <input 
                type='text'
                id='name'
                name='name'
                placeholder=''
                value={data.recipeName}
                onChange={handleChange}
              />
              <label htmlFor='description'>Beschrijving</label>
              <textarea
                id='description'
                name='description'
                placeholder=''
                value={data.description}
                onChange={handleChange}
              />
            <div className='type-region'>
              <label htmlFor='kitchenType'>Type</label>
              <select 
                id='kitchenType'
                name='kitchenType'
                value={data.type}
                onChange={handleChange}
              >
                {types.map(type => {
                  return (
                    <option value={type.id}>{type.name}</option>
                  )
                })}
              </select>

              <label htmlFor="kitchenRegion">Keuken</label>
              <select 
                id="kitchenRegion"
                name='kitchenRegion'
                value={data.region}
                onChange={handleChange}
              >
                {regions.map(region => {
                  return (
                    <option value={region.id}>{region.name}</option>
                  )
                })}
              </select>
            </div>
        </div>
      )
    }
  }

  return (
    renderContent()
  )
}

export default AddRecipeDetails
