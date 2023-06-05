import { useState, useEffect } from 'react'
import { AgendaIconInterface } from './AgendaIcon.interface'
import { BsCalendarCheckFill } from "react-icons/bs";


const AgendaIcon = ({testID, color, size }:AgendaIconInterface) => {

    let iconSize = 12;
    if (size === 's') iconSize = 20;
    if (size === 'm') iconSize = 32;
    if (size === 'l') iconSize = 48;
    return(
        <div data-testid={ testID }
             className={ `AgendaIcon ${ size }` }>
                <BsCalendarCheckFill size={iconSize} color={color}/>
        </div>
    )

}

export default AgendaIcon
