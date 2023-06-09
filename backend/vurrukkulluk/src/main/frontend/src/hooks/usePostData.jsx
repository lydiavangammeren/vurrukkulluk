import {useState} from 'react'
import api from "../lib/recipeAPI";

const usePostData = () => {
  const [data, setData] = useState({status: 0, payLoad: undefined});
  const [isLoaded, setLoaded] = useState(false);

  const token = localStorage.getItem('user') === null ? '' : 'Bearer ' + JSON.parse(localStorage.getItem('user')).token

  const headers = {
    'Content-Type': 'application/json',
    'Authorization': token 
  }

  const postData = async (url, body) => {
    setLoaded(false)
    console.log('Token : ' + token)

    console.log('Body: ' , JSON.stringify(body))
    
    try{
      const response = await api.post(url,
        JSON.stringify(body),
        {
          headers: headers
          // headers: {'Content-Type': 'application/json', Authorization: token }
        }
      );

      setData({status: response.status, payLoad: response.data});
      setLoaded(true);

    } catch(err){
      if(err.response){
        setData({status: err.response.status, payLoad: err.response.data});
        //Not in the 200 response range
        console.log(err.response.data);
        console.log(err.response.status);
        console.log(err.response.headers);
      }else{
        console.log(`Error: ${err.message}`)
        setData({status: 500, payLoad: err.message});
      }
      setLoaded(true);
    }
  }

  return [data, isLoaded, postData];
}

export default usePostData
