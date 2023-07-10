import { useState } from 'react'
import api from "../lib/recipeAPI"


const useSlug = () => {
  var slugify = require('slugify');
  var slug = '';
  const [ready, setReady] = useState(false);
  const [counter, setCounter] = useState(1);

  const slugIt = async (name) => {
    var slug = slugify(name, {lower: true, strict: true});
    var tempSlug = slug;
    // setReady(false);
    var rady = false;
    while(!rady){
      try{
        let response = await api.get(`/recipes/${tempSlug}`)
        console.log('Slug response: ', response);
        // setReady(true);
        // rady = true;
        tempSlug = slug + '-' + counter
        setCounter(prev => prev++);
      }catch(error){
        console.log('Slug Error: ', error)
        slug = tempSlug;
        rady = true;
      }
    }
    return slug;
  }


  return [slug, ready, slugIt];
}

export default useSlug
