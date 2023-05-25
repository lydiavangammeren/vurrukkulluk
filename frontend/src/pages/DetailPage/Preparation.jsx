import React from 'react';
import PreparationStep from './PreparationStep';

const Preparation = ({prepsteps}) => {
    return ( 
    <div className='Preparation'>
        {prepsteps && prepsteps.map((prepstep) => (
            // <h4>{`${prepstep.step} ${prepstep.desc}`}</h4>
            <PreparationStep step={prepstep.step}
                             description={prepstep.desc}/>
        ))}
        <h4>{}</h4>
        <h4>{}</h4>

    </div> );
}
 
export default Preparation;