import { PixelCrop } from 'react-image-crop'

const TO_RADIANS = Math.PI / 180

export async function canvasPreview(
  image,
  canvas,
  crop,
  scale = 1,
  rotate = 0,
) {
  console.log('before: ', canvas)
  console.log('crop: ', crop)
  console.log('image: ', image)
  const ctx = canvas.getContext('2d')

  if (!ctx) {
    throw new Error('No 2d context')
  }

  const scaleX = image.naturalWidth / image.width
  const scaleY = image.naturalHeight / image.height

  console.log(scaleX, scaleY)
  // devicePixelRatio slightly increases sharpness on retina devices
  // at the expense of slightly slower render times and needing to
  // size the image back down if you want to download/upload and be
  // true to the images natural size.
  const pixelRatio = window.devicePixelRatio
  console.log('Pixel Ratio: ', pixelRatio)
  // const pixelRatio = 1

  canvas.width = Math.floor(crop.width * scaleX * pixelRatio)
  canvas.height = Math.floor(crop.height * scaleY * pixelRatio)

  console.log('canvaswidth: ', canvas.width)
  // canvas.width = 1680;
  // canvas.height = 630;

  // ctx.scale(pixelRatio, pixelRatio)
  // ctx.imageSmoothingQuality = 'high'

  const cropX = crop.x * scaleX
  const cropY = crop.y * scaleY
  // const cropX = 1680
  // const cropY = 630

  const rotateRads = rotate * TO_RADIANS

  // const centerX = image.naturalWidth / 2
  // const centerY = image.naturalHeight / 2

  const centerX = image.height / 2
  const centerY = image.width / 2

  // const resizeX = (image.naturalHeight/image.naturalWidth) * 1680;

  // console.log('resize: ', resizeX)

  ctx.save()

  // 5) Move the crop origin to the canvas origin (0,0)
  ctx.translate(-cropX, -cropY)
  // 4) Move the origin to the center of the original position
  ctx.translate(centerX, centerY)
  // 3) Rotate around the origin
  ctx.rotate(rotateRads)
  // 2) Scale the image
  ctx.scale(scale, scale)
  // 1) Move the center of the image to the origin (0,0)
  ctx.translate(-centerX, -centerY)
  ctx.drawImage(
    image,
    // 0,
    // 0,
    // 1680,
    // 640,
    // 0,
    // 0,
    // 1680,
    // 640
    0,
    0,
    image.naturalWidth,
    image.naturalHeight,
    0,
    0,
    image.naturalWidth,
    image.naturalHeight,
  )

  console.log('context', ctx)
  ctx.restore()
  console.log('context 2', canvas)

}