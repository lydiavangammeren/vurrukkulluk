import React, {useState, useEffect} from "react";
import { useParams } from "react-router-dom";
import Details from "./Details";
import TabWrapper from "./TabWrapper";

const DetailContent = () => {
  
  const { id } = useParams();

  const [recipe, setRecipe] = useState({});

    const fetchRecipe = () =>{
      fetch(`http://localhost:3004/recipes/${id}`).then(response => response.json()).then((json) => setRecipe(json));
    }

    useEffect(() => {
      console.log('onMount')
      fetchRecipe();
    }, [])


  return (
    <div className='DetailContent'>
      <div className='detailTop'>
        <Details title={recipe.title}
                  image={recipe.image}
                  kitchen={recipe.kitchen}
                  type={recipe.type}
                  description={recipe.description}/>
      </div>
      <div className="detailBottom">
        <TabWrapper />
      </div>
    </div>
  );
};

export default DetailContent;
