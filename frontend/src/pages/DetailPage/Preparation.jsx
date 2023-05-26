import React from 'react';
import PreparationStep from './PreparationStep';

const Preparation = ({prepsteps}) => {
    return ( 
    <div className='Preparation'>
        {prepsteps && prepsteps.map((prepstep, index) => (
            // <h4>{`${prepstep.step} ${prepstep.desc}`}</h4>
            <PreparationStep prepstep={prepstep} key={index}/>
        ))}
        <h4>{}</h4>
        <h4>{}</h4>

    </div> );
}
 
export default Preparation;