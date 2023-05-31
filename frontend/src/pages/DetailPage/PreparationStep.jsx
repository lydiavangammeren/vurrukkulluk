import React from 'react';

const PreparationStep = ({prepstep}) => {
    return (
        <div className="step">
        <div className="numberCircle">
            <span>{prepstep.step}.</span>
        </div>
            <span>{prepstep.instructions}</span>
        </div>
    );
}
 
export default PreparationStep;