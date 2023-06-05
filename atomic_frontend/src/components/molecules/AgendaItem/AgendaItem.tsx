import { useState, useEffect } from 'react'
import { AgendaItemInterface } from './AgendaItem.interface'

/// TODO: update interface/arguments for AgendaItem
const AgendaItem = ({testID, style, type, size, props }:AgendaItemInterface) => {

    return(
        <div data-testid={ testID }
             data-object-type={ type ?? ""}
             className={ `AgendaItem ${ size } more utility classes` }>
        </div>
    )

}

export default AgendaItem
