import React from 'react'
import "./Category.css"

const Category = ({name}) => {
  return (
    <div className='category'>
      <span>&#8226; {name}</span>
    </div>
  )
}

export default Category
