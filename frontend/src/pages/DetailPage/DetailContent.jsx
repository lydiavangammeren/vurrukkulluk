import React, {useState, useEffect} from "react";
import { useParams } from "react-router-dom";
import Details from "./Details";
import TabWrapper from "./TabWrapper";

import api from "../../lib/recipeAPI";


const DetailContent = ({setImages}) => {
  
  const { id } = useParams();
  const [details, setDetails] = useState({});

  const ingredients = details?.ingredients ?? [];
  const prepsteps = details?.preparation ?? [];
  const comments = details?.comments ?? [];

  useEffect(() => {
    const getData = async () => {
      try{
        const response = await api.get(`/details/${id}`);
        setDetails(response.data);
        setImages([{src:response.data.image, alt:response.data.image}]);
        // console.log(response.data);
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
  }, [id, setImages])

  return (
    <div className='DetailContent'>
      <div className='detailTop'>
        <Details title={details.title}
                  image={details.image}
                  kitchen={details.kitchen}
                  type={details.type}
                  description={details.description}
                  persons={details.persons} price={details.price} calories={details.calories}/>
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
