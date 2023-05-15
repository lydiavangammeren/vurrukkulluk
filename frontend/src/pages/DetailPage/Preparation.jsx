import React from 'react';
import PreparationStep from './PreparationStep';

const Preparation = (props) => {
    return ( <div>
        <h3>Preparation</h3>
        {props.prepsteps.map((prepstep) => (
        <>
            <PreparationStep prepstep={prepstep}/>
          
        </>
      ))}
        <h4>{}</h4>
        <h4>{}</h4>

    </div> );
}
 
export default Preparation;