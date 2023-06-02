import { useState, useEffect } from "react";
import api from "../lib/recipeAPI";

const useDatabase = (url) => {
  const [data, setData] = useState(null);
  const [isLoaded, setLoaded] = useState(false);

  useEffect(() => {
    const getData = async () => {
      try{
        const response = await api.get(url);
        setData(response.data);
        setLoaded(true);
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

export default useDatabase;

