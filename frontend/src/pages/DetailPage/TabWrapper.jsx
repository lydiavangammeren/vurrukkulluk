// Wrapper component for tabs
import React from "react";

import Tabs from "./Tabs";
import Ingredients from "./Ingredients";
import Preparation from "./Preparation";
import Comments from "./Comments";

const TabWrapper = ({ingredients, prepsteps, comments, recipeId}) => {

  return (
    <>
      <Tabs>
        <div label="Ingredienten">
          <Ingredients ingredients={ingredients}/>
        </div>
        <div label="Bereidingswijze">
          <Preparation prepsteps={prepsteps} />
        </div>
        <div label="Opmerkingen">
          <Comments comments={comments} recipeId={recipeId}/>
        </div>
      </Tabs>
    </>
  );
};

export default TabWrapper;
