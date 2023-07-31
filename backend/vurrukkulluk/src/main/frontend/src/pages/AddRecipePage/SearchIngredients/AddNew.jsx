import React from 'react';
import "./AddNew.css";

const AddNew = () => {
  const ref = document.querySelector("#add_article_modal")
  // console.log('ref: ', ref)
  const openDialog = () => {
    ref.showModal();
  }

  return (
    <div className='add_new'>
      <button 
        type='button' 
        className='add_article_btn'
        onClick={openDialog}>
          Voeg nieuw artikel toe
        </button>
    </div>
  )
}

export default AddNew
