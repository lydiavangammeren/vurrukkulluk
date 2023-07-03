import React, { useState, useRef } from 'react'
import { useDatabase } from '../../hooks';
// import { useAppContext } from '../../contexts';
import useAddRecipeContext from '../../hooks/useAddRecipeContext';
import { SearchBar, SearchResultsList} from "./SearchCategories";
import ImageCrop from '../../components/ImageCrop/ImageCrop';
// import useImageResizer from '../../hooks/useImageResizer';


const DetailsForm = () => {
  const [types, typesLoaded ] = useDatabase('/kitchentypes');
  const [regions, regionsLoaded] = useDatabase('/kitchenregions');
  const [categories, categoriesLoaded] = useDatabase('/categories');
  // const { recipes } = useAppContext();
  const modalRef = useRef();
  const { data, handleChange, removeItem, selectedImage, setSelectedImage } = useAddRecipeContext();
  // const [image, imageResized, resize] = useImageResizer()

  const [searchValue, setSearchValue] = useState('');

  // const modal = document.querySelector("[data-modal]")
  // const closeButton = document.querySelector("[data-close-modal]")

  // closeButton.addEventListener("click", () => {modal.close()})

  // const [selectedImage, setSelectedImage] = useState(null);

  const handleImageUpload = (event) => {
    const file = event.target.files[0];
    // setImage(file)

    const reader = new FileReader();
    console.log('reader: ', reader)

    reader.onload = () => {
      console.log('Image: ', reader.result)
      
      // setSelectedImage(reader.result);
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
              <option value={0}>Kies een type</option>
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
              <option value={0}>Kies een regio</option>
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
              // value={image}
              onChange={handleImageUpload}
            />
            <div className='image_example'>
              {selectedImage && 
                <img src={selectedImage.src} alt='Uploaded'/>
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
            <label htmlFor='description'>Beschrijving</label>
            <textarea
              id='description'
              name='description'
              rows={15}
              value={data.description}
              onChange={handleChange}
            />
          </div>
          {/* Modal om te image te croppen: */}
          <dialog data-modal ref={modalRef}>
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

            {/* <img src={selectedImage.src} alt='alt' style={{width: '100%'}}/> */}
            {/* <button type='button' onClick={() =>{modalRef.current.close()}}>Sluiten</button> */}
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
