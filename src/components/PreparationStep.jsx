import React from 'react';

const PreparationStep = (props) => {
    return (<>
    <h5>{props.prepstep.step}</h5>
    <p>{props.prepstep.desc}</p>
    </>  );
}
 
export default PreparationStep;