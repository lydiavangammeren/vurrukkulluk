import React, { useState, useRef, useEffect } from 'react'
import { useDatabase } from '../../hooks';
import { useAppContext } from '../../contexts';
import useAddRecipeContext from '../../hooks/useAddRecipeContext';
import { SearchBar, SearchResultsList} from "./SearchCategories";
import ImageCrop from '../../components/ImageCrop/ImageCrop';
// import useImageResizer from '../../hooks/useImageResizer';
// import {BsCheckSquare, BsXSquare} from "react-icons/bs";
import validate from 'validate.js';
import { constraints } from '../../constraints/recipeDetails';

// const NAME_REGEX = /^[A-z][A-z0-9-_]{1,23}$/;

validate.validators.optionPicked = function(value, options, key, attributes) {
  if(value == 0){
    return "^Maak een keuze"
  }
}

const DetailsForm = () => {
  const [types, typesLoaded ] = useDatabase('/kitchentypes');
  const [regions, regionsLoaded] = useDatabase('/kitchenregions');
  const [categories, categoriesLoaded] = useDatabase('/categories');
  const {setExampleImage} = useAppContext();
  // const { recipes } = useAppContext();
  const modalRef = useRef();
  const { data, handleChange, removeItem, selectedImage, setSelectedImage, errors, setErrors } = useAddRecipeContext();
  const [searchValue, setSearchValue] = useState('');

  // const [errors, setErrors] = useState({});

  const validateInput = (e) => {
    const name = e.target.name;

    let validationErrors = validate(data, constraints);

    if(validationErrors && validationErrors[name]){
      setErrors(values => ({...values, [name]: validationErrors[name]}))
    } else{
      setErrors(values => ({...values, [name]: ""}))
    }

    // try {
    //   // console.log('to Validate: ', data)
    //   let response = await validate.async(data, constraints);
    //   // console.log('validation Succes: ', response)
    //   setErrors({})
    //   // console.log('errors state on succes: ', errors)
    // } catch(err){
    //   console.log('validation Errors: ', err)
    //   // setErrors(err)
    //   if(err[name]){
    //     setErrors(values => ({...values, [name]: err[name]}))
    //   } else{
    //     setErrors(values => ({...values, [name]: ""}))
    //   }
    // }
  }

  const handleImageUpload = (event) => {
    const file = event.target.files[0];
    const reader = new FileReader();
    reader.onload = () => {
      setSelectedImage({file: file, src: reader.result});
      
      modalRef.current.showModal();
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
    const foundObject = categories.find((object) => object.id === parseInt(id));
    return foundObject ? foundObject.name : 'Object not found';
  };

  useEffect(() => {
    setExampleImage(selectedImage.src);
  }, [selectedImage])


  const renderContent = () => {
    if(typesLoaded && regionsLoaded && categoriesLoaded){
      return (
        <div className='details_form'>
          <div className='details_grid_name grid_item'>
            <label htmlFor='name'>
              Naam
              {/* <BsCheckSquare color='green' className={validName? "valid" : "hide"}/>
              <BsXSquare color='red' className={validName || !data.name? "hide" : "invalid"}/> */}
            </label>
            <input 
              type='text'
              id='name'
              name='name'
              placeholder=''
              value={data.name}
              onChange={handleChange}
              onBlur={(e) => validateInput(e)}
            />
            {errors.name && errors.name.map((error, index) => {
              return <li className="error-message" key={index}>{error}</li>
            })}
          </div>
          <div className='details_grid_type grid_item'>
            <label htmlFor='type'>
              Type
              {/* <BsCheckSquare color='green' className={validType && data.type != 0 ? "valid" : "hide"}/>
              <BsXSquare color='red' className={validType ? "hide" : "invalid"}/> */}
            </label>
            <select 
              id='type'
              name='type'
              value={(data.type)}
              onChange={handleChange}
              onBlur={(e) => validateInput(e)}
            >
              <option value={0}>Kies een type</option>
              {types.map(type => {
                return (
                  <option value={type.id} key={type.id}>{type.name}</option>
                )
              })}
            </select>
            {errors.type && errors.type.map((error, index) => {
              return <li className="error-message" key={index}>{error}</li>
            })}
          </div>
          <div className='details_grid_kitchen grid_item'>
            <label htmlFor="region">
              Keuken
              {/* <BsCheckSquare color='green' className={validRegion ? "valid" : "hide"}/>
              <BsXSquare color='red' className={validRegion ? "hide" : "invalid"}/> */}
            </label>
            <select 
              id="region"
              name='region'
              value={data.region}
              onChange={handleChange}
              onBlur={(e) => validateInput(e)}
            >
              <option value={0}>Kies een regio</option>
              {regions.map(region => {
                return (
                  <option value={region.id} key={region.id}>{region.name}</option>
                )
              })}
            </select>
            {errors.region && errors.region.map((error, index) => {
              return <li className="error-message" key={index}>{error}</li>
            })}
          </div>
          <div className='details_grid_persons grid_item'>
            <label htmlFor='persons'>
              Aantal personen
              {/* <BsCheckSquare color='green' className={validPersons ? "valid" : "hide"}/>
              <BsXSquare color='red' className={validPersons ? "hide" : "invalid"}/> */}
            </label>
            <input 
              type='number'
              id='persons'
              name='persons'
              value={data.persons}
              onChange={handleChange}
              min={1}
              onBlur={(e) => validateInput(e)}
            />
            {errors.persons && errors.persons.map((error, index) => {
              return <li className="error-message" key={index}>{error}</li>
            })}
          </div>
          <div className='details_grid_image grid_item'>
            <label htmlFor='image'>
              Afbeelding
              {/* <BsCheckSquare color='green' className={validImage ? "valid" : "hide"}/>
              <BsXSquare color='red' className={validImage ? "hide" : "invalid"}/> */}
            </label>
            <input 
              type='file'
              accept='image/*'
              id='recipeImage' 
              name='recipeImage'
              // value={image}
              onChange={handleImageUpload}
            />
            <div className='image_example'>
              {selectedImage.src !== null ? 
                
                <img src={selectedImage.src} alt='Uploaded'/> :
                <img src="/images/plate.jpg" />
              }
            </div>
          </div>
          <div className='details_grid_categories grid_item'>
            <label htmlFor='categories'>Categorieën</label>

            <SearchBar searchValue={searchValue} setSearchValue={setSearchValue}/>
            {searchResults && searchResults.length > 0 && 
            <SearchResultsList results={searchResults} setSearchValue={setSearchValue}/>}

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
            <label htmlFor='description'>
              Beschrijving
              {/* <BsCheckSquare color='green' className={validDescription ? "valid" : "hide"}/>
              <BsXSquare color='red' className={validDescription || !data.description ? "hide" : "invalid"}/> */}
            </label>
            <textarea
              id='description'
              name='description'
              rows={15}
              value={data.description}
              onChange={handleChange}
              onBlur={(e) => validateInput(e)}
            />
            {errors.description && errors.description.map((error, index) => {
              return <li className="error-message" key={index}>{error}</li>
            })}
          </div>
          {/* Modal om te image te croppen: */}
          <dialog className='cropModal' data-modal ref={modalRef}>
            <ImageCrop 
              selectedImage={selectedImage}
              aspect={ 8 / 3}
              preview_width={320}
              preview_heigth={240}
              max_target_width={10000}
              min_target_width={480}
              setImage={setSelectedImage}
              modalRef={modalRef}
            />
          </dialog>
        </div>
      )
    }
  }

  return (
    renderContent()
  )
}

export default DetailsForm
