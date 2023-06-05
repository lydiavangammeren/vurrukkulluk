import { useState, useEffect } from 'react'
import { LabelInterface } from './Label.interface'

/// TODO: update interface/arguments for Label
const Label = ({testID, style, size, value }:LabelInterface) => {

    return(
        <div data-testid={ testID }
             className={ `Label ${ size } more utility classes` }>
                {value}
        </div>
    )

}

export default Label
