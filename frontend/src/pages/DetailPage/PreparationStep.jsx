import React from 'react';

const PreparationStep = ({step, description}) => {
    return (
        <div className="step">
        <div className="numberCircle">{step}.</div>
            <span>{description}</span>
        </div>
    );
}
 
export default PreparationStep;