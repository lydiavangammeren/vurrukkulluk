import React, {useEffect, useState} from 'react';
import useAddRecipeContext from '../../../hooks/useAddRecipeContext';
import usePostData from '../../../hooks/usePostData';
import usePostImage from '../../../hooks/usePostImage';
import { useDatabase } from '../../../hooks';

const defaultUnits = [
  {id: 10, name: 'stuks', selected: false},
  {id: 2, name: 'gram', selected: false},
  {id: 1, name: 'kilogram', selected: false},
  {id: 5, name: 'mililiter', selected: false},
  {id: 4, name: 'liter', selected: false}
]

export const AddNewArticle = ({setNew}) => {

  const { data, addItem } = useAddRecipeContext();
  const [article, articleLoaded, postArticle ] = usePostData();
  const [image, imageLoaded, postImage] = usePostImage();
  const [units, unitsLoaded] = useDatabase('/units');

  const [articleData, setArticleData] = useState({
    imageSrc: '',
    imageFile: null,
    name: '',
    amount: 0,
    defaultUnit: '',
    unitIds: {}
  });

  // const [unitIds, setUnitIds] = useState([])

  const handleChange = e => {
    const name = e.target.name;
    const value = e.target.value;

    // Voeg default nog toe:
    if(name === "defaultUnit"){
      if(value) {
        defaultUnits.forEach((unit)=>{
          const defaultRef = document.querySelector(`#unit-${unit.id}`)
          if(unit.id == value) {
            defaultRef.checked = true;
          } else{
            defaultRef.checked = false;
          }
        })
      }
    }

    setArticleData(prevData => ({
      ...prevData,
      [name]: e.target.value
    }))
  }

  const handleAmountChange = e => {
    const value = e.target.value;
    const name = e.target.name;
    const clonedUnitIds = {...articleData.unitIds}
    clonedUnitIds[name] = value;
    setArticleData(prevData => ({
      ...prevData,
      unitIds: clonedUnitIds
    }))
  }

  const handleCheckbox = (e) => {
    console.log('handleCheckbox: ', e)
    // const name = e.target.name;
    const checked = e.target.checked;
    const value = e.target.value;
    const amountInput = document.querySelector(`#amount-unit-${value}`)
    const hideInput = document.querySelector(`#hide-unit-${value}`)

    if(!checked){
      amountInput.classList.toggle('hide')
      hideInput.classList.toggle('hide')
      const clonedUnitIds = {...articleData.unitIds}
      delete clonedUnitIds[value];
      setArticleData(prevData => ({
        ...prevData,
        // if does exist, remove unit.
        // unitIds: articleData.unitIds.filter((unitId)=>{return unitId !== value})
        unitIds: clonedUnitIds
      }))
    } else{
      amountInput.classList.toggle('hide')
      hideInput.classList.toggle('hide')
      const clonedUnitIds = {...articleData.unitIds}
      clonedUnitIds[value] = 1; // Value nog uit input halen !!
      setArticleData(prevData => ({
        ...prevData,
        // if does not exist, add unit.
        // unitIds: [...articleData.unitIds, value] 
        unitIds: clonedUnitIds
      }))
    }
  }

  const handleImageUpload = (e) => {
    const file = e.target.files[0];
    const reader = new FileReader();

    reader.onload = () => {
      setArticleData(prevData => ({
        ...prevData,
        imageFile: file, imageSrc: reader.result
      }))
    };
    if (file) {
      reader.readAsDataURL(file);
    }
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    
    var tempUnits = articleData.unitIds;
    tempUnits[articleData.defaultUnit] = "1";

    const user = JSON.parse(localStorage.getItem('user'));

    if(!user) {
      alert('Je moet ingelogd zijn');
      return;
    }

    const body = {
      name: articleData.name,
      description: 'Door gebruiker toegevoegd',
      price: 0,
      calories: 0,
      amount: articleData.amount,
      imageId: 1,
      userId: user.id,
      defaultUnitId: articleData.defaultUnit,
      units: tempUnits,
      available: false,
    }

    console.log(body);
    postArticle('/articles', body)
  }

  const getUnitById = (id) => {
    if(!unitsLoaded || !id) return '';
    console.log('unit: ', id)
    var unitName = units?.find((unit) => {
      return unit.id == id;
    }).name ?? '';
    return unitName;
  }

  useEffect(() => {
    if(articleLoaded){
      
      postImage('/image?type=article&id=' + article.payLoad, {image: articleData.imageFile})
    }
  }, [articleLoaded])

  useEffect(() =>{
    if(image.status != 200) return;
    setNew(true);
  }, [imageLoaded])

  const canSubmit = () => {
    if(![...Object.values(articleData)].every(Boolean)) return false;
    return true;
  }
  return (
    <div className="add_article">
      <h2>Voeg artikel toe</h2>
      <form className='add_article_form'>
      <div className='add_article_fields'>
        <div className='add_article_field'>
          <label htmlFor='image'>Afbeelding</label>
          <input
            type='file'
            id='image'
            onChange={handleImageUpload}/>
          <div className='article_img_example'>
            {articleData.imageSrc && 
              <img 
                src={articleData.imageSrc} 
                alt='Uploaded'
              />
            }
          </div>
        </div>
        <div className='article_details'>
          <div className='add_article_field'>
            <label htmlFor='name'>Naam</label>
            <input 
              type='text'
              id='name'
              name='name'
              onChange={handleChange}/>
          </div>
          <div className='add_article_field'>
            <label htmlFor='amount'>Hoeveelheid</label>
            <input 
              type='number'
              id='amount'
              name='amount'
              onChange={handleChange}/>
            <select name='defaultUnit' onChange={handleChange}>
              <option value={''}>Kies</option>
              {defaultUnits.map((unit)=>{
                return <option value={unit.id} key={`option-${unit.id}`}>{unit.name}</option>
              })}
              {/* <option value={10}>stuks</option>
              <option value={2}>gram</option>
              <option value={1}>kilogram</option>
              <option value={5}>mililiter</option>
              <option value={4}>liter</option> */}
            </select>
          </div>
          {/* <div className='add_article_field'>
            <label htmlFor=''>Calorieën:</label>
            <input type='number' id='calories' min={1} value={1}/>
          </div>
          <div className='add_article_field'>
            <label htmlFor=''>Prijs:</label>
            <input type='number' id='price' min={1} value={1}/>
          </div> */}
        </div>
        <div className='add_article_units'>
          <table>
            <thead>
              <tr>
                <th>Eenheid:</th>
                <th></th>
                <th>{articleData.name}</th>
              </tr>
            </thead>
            <tbody>
          {units && unitsLoaded &&
          units.map((unit)=> {
            const isDefault = articleData.defaultUnit == unit.id;
            return (<tr key={`unitrow-${unit.id}`}>
                      <td>
                        <div>
                          <input 
                            type='checkbox' 
                            id={`unit-${unit.id}`} 
                            value={unit.id} 
                            onChange={handleCheckbox} 
                            // checked={isDefault}
                            disabled={isDefault}
                          />
                          <label htmlFor={`unit-${unit.id}`} >{unit.name}{isDefault ? '(default)' : ''}</label>
                        </div>
                      </td>
                      <td>=</td>
                      <td>
                        <div className={isDefault ? '' : 'hide'} id={`amount-unit-${unit.id}`}>
                          <input 
                            type='number' 
                            value={isDefault ? '1' : articleData.unitIds[unit.id]?? ''}
                            name={unit.id}
                            onChange={handleAmountChange}
                            disabled={isDefault}
                          />
                        </div>
                      </td>
                      <td>
                        <div className={isDefault ? '' : 'hide'} id={`hide-unit-${unit.id}`}>
                          {getUnitById(articleData.defaultUnit)}
                        </div>
                      </td>
                    </tr>)
          })}
            </tbody>
          </table>
        </div>
        </div>
        <div className='button_box'>
          <button 
            className='add_article_button'
            onClick={handleSubmit}
            disabled={!canSubmit()}
            >
            Voeg artikel toe
          </button>
        </div>
      </form>
    </div>
  )
}