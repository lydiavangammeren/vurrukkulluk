import React, {useState, useEffect} from "react";
import { useParams } from "react-router-dom";
import Details from "./Details";
import TabWrapper from "./TabWrapper";

import api from "../../lib/recipeAPI";


const DetailContent = () => {
  
  const { id } = useParams();
  const [details, setDetails] = useState({});
  const [ingredients, setIngredients] = useState([]);
  const [prepsteps, setPrepsteps] = useState([]);
  const [comments, setComments] = useState([]);

  useEffect(() => {
    const getData = async () => {
      try{
        const response = await api.get(`/details/${id}`);
        setDetails(response.data);
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
    // console.log(details.ingredients);
  }, [])

  useEffect(() => {
    setIngredients(details.ingredients);
    setPrepsteps(details.preparation);
    setComments(details.comments); 
  }, [details])


  return (
    <div className='DetailContent'>
      <div className='detailTop'>
        <Details title={details.title}
                  image={details.image}
                  kitchen={details.kitchen}
                  type={details.type}
                  description={details.description}/>
      </div>
      <div className="detailBottom">
        {ingredients && prepsteps && comments && 
          <TabWrapper ingredients={ingredients}
            prepsteps={prepsteps}
            comments={comments}/>
        }
      </div>
    </div>
  );
};

export default DetailContent;
