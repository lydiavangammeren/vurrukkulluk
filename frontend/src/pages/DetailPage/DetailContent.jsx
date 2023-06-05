import React from "react";
import { useParams } from "react-router-dom";
import Details from "./Details";
import TabWrapper from "./TabWrapper";
import { useAppContext } from "../../contexts";


const DetailContent = ({setImages}) => {
  
  const { slug } = useParams();
  
  const { recipes } = useAppContext();
  const details = recipes.find(recipe => recipe.slug === slug);
  
  const ingredients = details?.ingredients ?? [];
  const prepsteps = details?.preparation ?? [];
  const comments = details?.comments ?? [];

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
