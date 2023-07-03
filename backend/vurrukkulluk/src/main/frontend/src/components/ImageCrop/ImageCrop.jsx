import React, { useState, useRef, useEffect } from 'react'

import ReactCrop, {
  centerCrop,
  makeAspectCrop
} from 'react-image-crop'
import { canvasPreview } from './canvasPreview'
import { useDebounceEffect } from './useDebounceEffect'

import 'react-image-crop/dist/ReactCrop.css'
import './ImageCrop.css';
import useImageResizer from '../../hooks/useImageResizer'

// This is to demonstate how to make and center a % aspect crop
// which is a bit trickier so we use some helper functions.
function centerAspectCrop(
  mediaWidth,
  mediaHeight,
  aspect,
) {
  return centerCrop(
    makeAspectCrop(
      {
        unit: '%',
        width: 90,
      },
      aspect,
      mediaWidth,
      mediaHeight,
    ),
    mediaWidth,
    mediaHeight,
  )
}

const ImageCrop = ({
  selectedImage,
  // preview,
  aspect,
  preview_width,
  preview_heigth,
  // max_target_width,
  // min_target_width,
  setImage,
  modalRef
}) => {
  const imgSrc = selectedImage?.src;
  const previewCanvasRef = useRef(null)
  const imgRef = useRef(null)
  const blobUrlRef = useRef('')
  const [crop, setCrop] = useState()
  const [completedCrop, setCompletedCrop] = useState()
  const [scale, setScale] = useState(1)
  const [rotate, setRotate] = useState(0)

  const [resizedImage, isResized, resize, urlToFile] = useImageResizer()

  function onImageLoad(e) {
    if (aspect) {
      const { width, height } = e.currentTarget
      setCrop(centerAspectCrop(width, height, aspect))
    }
  }

  function onDownloadCropClick() {
    if (!previewCanvasRef.current) {
      throw new Error('Crop canvas does not exist')
    }

    previewCanvasRef.current.toBlob((blob) => {
      if (!blob) {
        throw new Error('Failed to create blob')
      }
      if (blobUrlRef.current) {
        URL.revokeObjectURL(blobUrlRef.current)
      }
      blobUrlRef.current = URL.createObjectURL(blob)

      const canvas = previewCanvasRef.current.toDataURL('image/jpeg', 90)

      setImage({
        src: canvas
      })
      modalRef.current.close()
    })
  }

  useDebounceEffect(
    async () => {
      if (
        completedCrop?.width &&
        completedCrop?.height &&
        imgRef.current &&
        previewCanvasRef.current
      ) {
        // We use canvasPreview as it's much faster than imgPreview.
        canvasPreview(
          imgRef.current,
          previewCanvasRef.current,
          completedCrop,
          scale,
          rotate,
        )
      }
    },
    100,
    [completedCrop, scale, rotate]
  )


  return (
    <div className="ImageCrop">
      <div>
      <h2>Crop uw afbeelding</h2>
        <div className="Crop-Controls">
          {/* <input type="file" accept="image/*" onChange={onSelectFile} /> */}
          <div>
            <label htmlFor="scale-input">Scale: </label>
            <input
              id="scale-input"
              type="number"
              step="0.1"
              value={scale}
              disabled={!imgSrc}
              onChange={(e) => setScale(Number(e.target.value))}
            />
          </div>
          <div>
            <label htmlFor="rotate-input">Rotate: </label>
            <input
              id="rotate-input"
              type="number"
              value={rotate}
              disabled={!imgSrc}
              onChange={(e) =>
                setRotate(Math.min(180, Math.max(-180, Number(e.target.value))))
              }
            />
          </div>
        </div>
        
        {!!imgSrc && (
          <ReactCrop
            className='demo_image'
            crop={crop}
            onChange={(_, percentCrop) => setCrop(percentCrop)}
            onComplete={(c) => setCompletedCrop(c)}
            // onComplete={(c) => setImage(c)}
            aspect={aspect}
          >
            <img
              ref={imgRef}
              alt="Crop me"
              src={imgSrc}
              style={{ transform: `scale(${scale}) rotate(${rotate}deg)` }}
              onLoad={onImageLoad}
            />
          </ReactCrop>
        )}
      </div>

      {!!completedCrop && (
        <>
          <div className='preview_image'>
            <h2>Preview</h2>
            <canvas
              ref={previewCanvasRef}
              style={{
                border: '1px solid black',
                objectFit: 'cover',
                // width: completedCrop.width,
                // height: completedCrop.height,
                width: preview_width, // aspect ratio for detailspage
                height: preview_heigth
              }}
            />
          </div>
          <div>
            <button type='button' onClick={onDownloadCropClick}>Klaar</button>
          </div>
        </>
      )}
    </div>
  )
}

export default ImageCrop
