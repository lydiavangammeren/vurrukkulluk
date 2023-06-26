import React, {useState} from 'react';
import useAddRecipeContext from '../../../hooks/useAddRecipeContext';
import usePostData from '../../../hooks/usePostData';
import usePostImage from '../../../hooks/usePostImage';

export const AddNewArticle = () => {

  const { data, addItem } = useAddRecipeContext();
  const [article, articleLoaded, postArticle ] = usePostData();
  const [image, imageLoaded, postImage] = usePostImage();
  
  const [articleData, setArticleData] = useState({
    imageSrc: '',
    imageFile: null,
    name: '',
    amount: 0,
    unit: ''
  });

  const handleChange = e => {
    const name = e.target.name
    setArticleData(prevData => ({
      ...prevData,
      [name]: e.target.value
    }))
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
    console.log(articleData);
  }



  return (
    <div className="add_article">
      <h2>Voeg artikel toe</h2>
      <form className='add_article_form'>
        <div className='add_article_field'>
          <label htmlFor='image'>Afbeelding</label>
          <input
            type='file'
            id='image'
            onChange={handleImageUpload}/>
          <div className='article_img_example'>
            {data.imageSrc && 
              <img 
                src={data.imageSrc} 
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
            <select name='unit' onChange={handleChange}>
              <option value={''}>Kies</option>
              <option value={'stuks'}>stuks</option>
              <option value={'g'}>g</option>
              <option value={'ml'}>ml</option>
            </select>
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