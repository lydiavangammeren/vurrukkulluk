import {useState} from 'react'
import Resizer from 'react-image-file-resizer';

const useImageResizer = () => {
  const [data, setData] = useState(null);
  const [isResized, setResized] = useState(false);

  const resizeFile = (file) =>
  new Promise((resolve) => {
    Resizer.imageFileResizer(
      file,
      300,
      300,
      "JPEG",
      100,
      0,
      (uri) => {
        resolve(uri);
      },
      "base64"
    );
  });

  const resize = async (file) => {
    try{
      const image = await resizeFile(file);
      setData(image);
      setResized(true);
    } catch(err){
      console.log(err);
    }
    
  }

  return [data, isResized, resize]
}

export default useImageResizer
