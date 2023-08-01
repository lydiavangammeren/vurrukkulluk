import React, {useEffect, useState} from 'react';
import useAddRecipeContext from '../../../hooks/useAddRecipeContext';
import usePostData from '../../../hooks/usePostData';
import usePostImage from '../../../hooks/usePostImage';
import { useDatabase } from '../../../hooks';

export const AddNewArticle = () => {

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
    const name = e.target.name
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
    

    const body = {
      name: articleData.name,
      description: 'Door gebruiker toegevoegd',
      price: 0,
      calories: 0,
      amount: articleData.amount,
      imageId: 1,
      userId: JSON.parse(localStorage.getItem('user')).id,
      defaultUnitId: articleData.defaultUnit,
      units: articleData.unitIds,
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
      postImage('/images?type=article&id=' + article.payLoad, articleData.imageFile)
    }
  }, [articleLoaded])

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
              <option value={10}>stuks</option>
              <option value={2}>gram</option>
              <option value={1}>kilogram</option>
              <option value={5}>mililiter</option>
              <option value={4}>liter</option>
            </select>
          </div>
          <div className='add_article_field'>
            <label htmlFor=''>Calorieën:</label>
            <input type='number' id='calories' min={1} value={1}/>
          </div>
          <div className='add_article_field'>
            <label htmlFor=''>Prijs:</label>
            <input type='number' id='price' min={1} value={1}/>
          </div>
        </div>
        <div className='add_article_units'>
          <table>
            <tr>
              <th>Eenheid:</th>
              <th></th>
              <th>{articleData.name}</th>
            </tr>
          {units && unitsLoaded &&
          units.map((unit)=> {
            return (<tr>
                      <td>
                        <div>
                          <input type='checkbox' id={`unit-${unit.id}`} value={unit.id} onChange={handleCheckbox}/>
                          <label htmlFor={`unit-${unit.id}`} >{unit.name}{articleData.defaultUnit == unit.id ? '(default)' : ''}</label>
                        </div>
                      </td>
                      <td>=</td>
                      <td>
                        <div className='hide' id={`amount-unit-${unit.id}`}>
                          <input 
                            type='number' 
                            value={articleData.defaultUnit == unit.id ? '1' : articleData.unitIds[unit.id]?? ''}
                            name={unit.id}
                            onChange={handleAmountChange}
                          />
                        </div>
                      </td>
                      <td>
                        <div className='hide' id={`hide-unit-${unit.id}`}>
                          {getUnitById(articleData.defaultUnit)}
                        </div>
                      </td>
                    </tr>)
          })}
          </table>
        </div>
        </div>
        <div className='button_box'>
          <button 
            className='add_article_button'
            onClick={handleSubmit}>
            Voeg artikel toe
          </button>
        </div>
      </form>
    </div>
  )
}