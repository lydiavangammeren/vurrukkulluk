import React from 'react'

const ObjectToArray = () => {
  const articles = [
    {"name":"Vegan Burger Bun","description":"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque eu metus sem. Sed lobortis tempor arcu. Nulla id nulla in nibh dictum feugiat. Donec sed accumsan est, at accumsan velit.","price":120,"calories":250,"unit":"stuks","amount":1,"imageId":2,"id":1},
    {"name":"Vegan Burger","description":"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque eu metus sem. Sed lobortis tempor arcu. Nulla id nulla in nibh dictum feugiat. Donec sed accumsan est, at accumsan velit.","price":540,"calories":469,"unit":"g","amount":400,"imageId":3,"id":2},
    {"name":"Vegan Burger Sauce","description":"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque eu metus sem. Sed lobortis tempor arcu. Nulla id nulla in nibh dictum feugiat. Donec sed accumsan est, at accumsan velit.","price":520,"calories":750,"unit":"ml","amount":250,"imageId":4,"id":3},
    {"name":"Adocado","description":"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque eu metus sem. Sed lobortis tempor arcu. Nulla id nulla in nibh dictum feugiat. Donec sed accumsan est, at accumsan velit.","price":200,"calories":300,"unit":"stuks","amount":1,"imageId":5,"id":4},
    {"name":"peper","description":"","price":0,"calories":0,"unit":"g","amount":20,"imageId":22,"id":8}
  ];

  const products = 
  {
    "1": 3,
    "2": 2,
    "3": 2,
    "4": 4
  }

  // let fullProducts = Object.keys(products).forEach(function(key, value) {
  //   console.log('key: ', key)
  //   console.log('value: ', value)
  //   return {
  //     article: articles.filter((article) => {return article.id == key}),
  //     amount: value
  //   }
  // })
  const fullProducts = Object.entries(products).map(([key, value]) => ({article: articles.filter((article) => {return article.id == key}), amount: value}));
  console.log('fullProducts: ', fullProducts)

  return (
    <div>
      
    </div>
  )
}

export default ObjectToArray
