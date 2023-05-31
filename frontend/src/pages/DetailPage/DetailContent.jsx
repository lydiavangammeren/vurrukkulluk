import React, {useState, useEffect} from "react";
import { useParams } from "react-router-dom";
import Details from "./Details";
import TabWrapper from "./TabWrapper";

import api from "../../lib/recipeAPI";


const DetailContent = ({setImages}) => {
  
  // const { id } = useParams();
  const { slug } = useParams();

  const [details, setDetails] = useState({});
  
  // const {info, ingredients, prepsteps, comments} = [...details, details.ingredients, details.preparation, details.comments];

  const ingredients = details?.ingredients ?? [];
  const prepsteps = details?.preparation ?? [];
  const comments = details?.comments ?? [];

  // const price = ingredients.length > 0 && ingredients.reduce((currentTotal, ingredient) => {
  //   return ingredient.article.price + currentTotal;
  // });

  useEffect(() => {
    const getData = async () => {
      try{
        const response = await api.get(`/details/${slug}`);
        // const response = await api.get(`/recipes/${slug}`);
        setDetails(response.data);
        setImages([{src:response.data.image, alt:response.data.image}]);
        // console.log(response.data.kitchenRegion.name);
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
  }, [slug, setImages])

  return (
    <div className='DetailContent'>
      <div className='detailTop'>
        <Details details={details}/>
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
