import React from 'react';
import "../css/PreparationStep.css";

const PreparationStep = (props) => {
    return (<>
    <div className="numberCircle">{props.prepstep.step}.</div>
    <p>{props.prepstep.desc}</p>
    </>  );
}
 
export default PreparationStep;