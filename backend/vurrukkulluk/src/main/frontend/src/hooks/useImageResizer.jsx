import {useState} from 'react'
import Resizer from 'react-image-file-resizer';

const WIDTH = 1680;

const useImageResizer = () => {
  const [data, setData] = useState(null);
  const [isResized, setResized] = useState(false);

  const resizeFile = (file) =>
    new Promise((resolve) => {
      Resizer.imageFileResizer(
        file,
        1680, // max width
        640, // max height
        "JPEG",
        90,
        0,
        (uri) => {
          resolve(uri);
        },
        "file"
      );
  });

  const resize = async (dataUrl) => {
    const file = urlToFile(dataUrl)
    try{
      const image = await resizeFile(file);
      // setData(urlToFile(image));
      setData(image)
      setResized(true);
      console.log('image resized: ', data)
    } catch(err){
      console.log(err);
    }
  }

  const urlToFile = (url) => {
    const arr = url.split(",")

    const mime = arr[0].match(/:(.*?);/)[1]
    const data = arr[1]

    const dataStr = atob(data)
    let n = dataStr.length
    let dataArr = new Uint8Array(n)

    while(n--){
      dataArr[n] = dataStr.charCodeAt(n)
    }

    const file = new File([dataArr], 'newImage.jpg', {type: mime})

    console.log(file);
    // setData(file);
    // setResized(true);
    // resize(file)

    return file;

  }

  return [data, isResized, resize, urlToFile]
}

export default useImageResizer
