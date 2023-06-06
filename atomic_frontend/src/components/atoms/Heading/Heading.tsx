import { useState, useEffect, createElement } from 'react'
import { HeadingInterface } from './Heading.interface'

/// TODO: update interface/arguments for Heading
const Heading = ({testID, size, text, colorVar }:HeadingInterface) => {
    let tag;
    switch(size) {
        case "xl": tag = "h1"; break;
        case "l":  tag = "h2"; break;
        case "m":  tag = "h3"; break;
        case "s":  tag = "h4"; break;
        default:   tag = "h5"; break;
    }
    return createElement( tag, { 
        "data-testid": testID, 
        "data-object-color": colorVar ?? "darker-green",  
        className: `Heading ${size}`,
    }, text)
}

export default Heading
