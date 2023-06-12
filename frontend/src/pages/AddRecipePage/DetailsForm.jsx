import React from 'react'
import { useDatabase } from '../../hooks';
import { useAppContext } from '../../contexts';
import './DetailsForm.css';
import useAddRecipeContext from '../../hooks/useAddRecipeContext';

const DetailsForm = () => {
  const [types, typesLoaded ] = useDatabase('/kitchentypes');
  const [regions, regionsLoaded] = useDatabase('/kitchenregions');
  const [categories, categoriesLoaded] = useDatabase('/kitchencategories');
  const { recipes } = useAppContext();
  const { data, handleChange } = useAddRecipeContext();

  const renderContent = () => {
    if(typesLoaded && regionsLoaded && categoriesLoaded){
      return (
        <div className='details_form'>
          <div className='details_grid_name'>
            <label htmlFor='name'>Naam</label>
            <input 
              type='text'
              id='name'
              name='name'
              placeholder=''
              value={data.name}
              onChange={handleChange}
            />
          </div>
          <div className='details_grid_type'>
            <label htmlFor='type'>Type</label>
            <select 
              id='type'
              name='type'
              value={(data.type)}
              onChange={handleChange}
            >
              {types.map(type => {
                return (
                  <option value={type.id}>{type.name}</option>
                )
              })}
            </select>
          </div>
          <div className='details_grid_kitchen'>
            <label htmlFor="region">Keuken</label>
            <select 
              id="region"
              name='region'
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
          <div className='details_grid_persons'>
            <label htmlFor='persons'>Aantal personen</label>
            <input type='number' />
          </div>
          <div className='details_grid_image'>
            <label htmlFor='image'>Afbeelding</label>
            <input 
              type='file' 
              id='recipeImage' 
              name='recipeImage'
                         
            />
            <div className='image_example'>

            </div>
          </div>
          <div className='details_grid_categories'>
            <label htmlFor='categories'>Categorieën</label>
            {/* <select 
              id="categories"
              name='categories'
              value={data.categories}
              onChange={handleChange}
            >
              {categories.map(category => {
                return (
                  <option value={category.id}>{category.name}</option>
                )
              })}
            </select> */}
            <input type='text' list='allCategories' />
            <datalist id='allCategories'>
              {categories.map(category => {
                return (
                  <option value={category.id}>{category.name}</option>
                )
              })}
            </datalist>
            <div className='category_list'>

            </div>
          </div>
          <div className='details_grid_desc'>
            <label htmlFor='description'>Beschrijving</label>
            <textarea
              id='description'
              name='description'
              placeholder=''
              value={data.description}
              onChange={handleChange}
            />
          </div>
        </div>
      )
    }
  }



  return (
    renderContent()
  )
}

export default DetailsForm
