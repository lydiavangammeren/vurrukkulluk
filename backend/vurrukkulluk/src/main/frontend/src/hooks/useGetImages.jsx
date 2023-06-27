import { useState, useEffect } from "react";
import api from "../lib/recipeAPI";

const useGetImages = (url, params) => {
  const [data, setData] = useState(null);
  const [isLoaded, setLoaded] = useState(false);

  const request = {
    params:
    {
      id:params
    }
  };

  useEffect(() => {
    const getData = async () => {
      try{
        const response = await api.get(url, request);
        setData(response.data);
        setLoaded(true);
        console.log('Images from database: ');
        console.log(response.data);
      } catch(err){
        if(err.response){
          //Not in the 200 response range
          console.log(err.response.data);
          console.log(err.response.status);
          console.log(err.response.headers);
        }else{
          console.log(`Error: ${err.message}`)
        }
      }
    }
    getData();
  }, [])

  return [data, isLoaded];
}

export default useGetImages;