import React from 'react';
import "../css/PreparationStep.css";

const PreparationStep = (props) => {
    return (<>
    <div className="step">
    <div className="numberCircle">{props.prepstep.step}.</div>
    <span>{props.prepstep.desc}</span>    
    </div>   
    </>  );
}
 
export default PreparationStep;