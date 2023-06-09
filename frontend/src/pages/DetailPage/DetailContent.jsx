import React, { useEffect } from "react";
import { useParams, useLocation } from "react-router-dom";
import Details from "./Details";
import TabWrapper from "./TabWrapper";
import { useAppContext } from "../../contexts";


const DetailContent = () => {
  
  const { slug } = useParams();
  
  const { recipes, bannerImages, setDetailImage } = useAppContext();
  const details = recipes.find(recipe => recipe.slug == slug);
  
  const ingredients = details?.ingredients ?? [];
  const prepsteps = details?.preparation ?? [];
  const comments = details?.comments ?? [];

  useEffect(() => {
    setDetailImage([details.imageId]);
  }, [details.imageId]);


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
