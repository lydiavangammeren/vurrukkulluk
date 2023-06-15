import React, { useState } from 'react'
import { useDatabase } from '../../hooks';
import { useAppContext } from '../../contexts';
import './DetailsForm.css';
import useAddRecipeContext from '../../hooks/useAddRecipeContext';
// import SearchBar from '../../components/SearchBar/SearchBar';
// import { SearchResultsList } from '../../components/SearchBar/SearchResultsList';
import { SearchBar, SearchResultsList} from "./SearchCategories";

const DetailsForm = () => {
  const [types, typesLoaded ] = useDatabase('/kitchentypes');
  const [regions, regionsLoaded] = useDatabase('/kitchenregions');
  const [categories, categoriesLoaded] = useDatabase('/categories');
  const { recipes } = useAppContext();
  const { data, handleChange, removeItem, removeCategory } = useAddRecipeContext();

  const [searchValue, setSearchValue] = useState('');

  const [selectedImage, setSelectedImage] = useState(null);

  const handleImageUpload = (event) => {
    const file = event.target.files[0];
    const reader = new FileReader();

    reader.onload = () => {
      setSelectedImage(reader.result);
    };

    if (file) {
      reader.readAsDataURL(file);
    }
  };


  const filter = () => {
    if(!searchValue) return [];
    
    return categories.filter((category)=> {
      return (
        searchValue &&
        categoriesLoaded && (
          category.name.toLowerCase().includes(searchValue)
        )
      )
    });
  }

  const searchResults = filter();

  const findObjectNameById = (id) => {
    // console.log('Id to find: ' + id);
    // console.log('to search: ' + JSON.stringify(categories))
    const foundObject = categories.find((object) => object.id === parseInt(id));
    // console.log('Found object: ' + JSON.stringify(foundObject))
    return foundObject ? foundObject.name : 'Object not found';
  };

  const renderContent = () => {
    if(typesLoaded && regionsLoaded && categoriesLoaded){
      return (
        <div className='details_form'>
          <div className='details_grid_name grid_item'>
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
          <div className='details_grid_type grid_item'>
            <label htmlFor='type'>Type</label>
            <select 
              id='type'
              name='type'
              value={(data.type)}
              onChange={handleChange}
            >
              {types.map(type => {
                return (
                  <option value={type.id} key={type.id}>{type.name}</option>
                )
              })}
            </select>
          </div>
          <div className='details_grid_kitchen grid_item'>
            <label htmlFor="region">Keuken</label>
            <select 
              id="region"
              name='region'
              value={data.region}
              onChange={handleChange}
            >
              {regions.map(region => {
                return (
                  <option value={region.id} key={region.id}>{region.name}</option>
                )
              })}
            </select>
          </div>
          <div className='details_grid_persons grid_item'>
            <label htmlFor='persons'>Aantal personen</label>
            <input 
              type='number'
              id='persons'
              name='persons'
              value={data.persons}
              onChange={handleChange} 
            />
          </div>
          <div className='details_grid_image grid_item'>
            <label htmlFor='image'>Afbeelding</label>
            <input 
              type='file'
              accept='image/*'
              id='recipeImage' 
              name='recipeImage'
              onChange={handleImageUpload}
            />
            <div className='image_example'>
              {selectedImage && 
                <img src={selectedImage} alt='Uploaded'/>
              }
            </div>
          </div>
          <div className='details_grid_categories grid_item'>
            <label htmlFor='categories'>Categorieën</label>

            <SearchBar searchValue={searchValue} setSearchValue={setSearchValue}/>
            {searchResults && searchResults.length > 0 && <SearchResultsList results={searchResults} setSearchValue={setSearchValue}/>}

            <div className='category_list'>
              {data.categories.map((categoryId) => {
                // console.log('Print Category: ' + categoryId)
                
                return (
                  // <li>{findObjectNameById(categoryId)}</li>
                  <div className='category_item' key={categoryId}>
                    <span>{findObjectNameById(categoryId)}</span>
                    <button value={categoryId} name='categories' onClick={removeItem}>X</button>
                  </div>
                )
              })}
            </div>
          </div>
          <div className='details_grid_desc grid_item'>
            <label htmlFor='description'>Beschrijving</label>
            <textarea
              id='description'
              name='description'
              rows={15}
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
