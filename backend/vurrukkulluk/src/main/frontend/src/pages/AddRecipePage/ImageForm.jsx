import React, { useRef, useState } from 'react';
import "react-image-crop/dist/ReactCrop.css";
import ImageCrop from './ImageCrop/ImageCrop';

const ImageForm = () => {


  return (
    <div className='ImageForm'>
      <ImageCrop />
    </div>
  )
}

export default ImageForm
