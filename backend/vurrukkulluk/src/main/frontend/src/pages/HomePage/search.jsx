function arrayUnique(array) {
  var a = array.concat();
  for(var i=0; i<a.length; ++i) {
      for(var j=i+1; j<a.length; ++j) {
          if(a[i] === a[j])
              a.splice(j--, 1);
      }
  }

  return a;
}

function filter (query, documents) {
    if(!query) return documents;
    const filtered = documents.filter((recipe)=> {
      return (
        query && (
          recipe.title.toLowerCase().includes(query) ||
          recipe.kitchenRegion.name.toLowerCase().includes(query) ||
          recipe.kitchenType.name.toLowerCase().includes(query) ||
          recipe.categories.some(e => e.name.toLowerCase().includes(query)) || 
          recipe.description.toLowerCase().includes(query)
        )
      )
    });
    return filtered;
}

export function search(query, documents){

  console.log('searchValue: ', query)
  if(!query) return documents;
  // const searchValue = query.trim();

  // Split the search query into separate words
  const queryWords = query.trim().toLowerCase().split(' ');

  var results = [];

  results = filter(query, documents);

  queryWords.forEach((word)=> {
    const wordResults = filter(word, documents)
    results = [...results, ...wordResults];
  })
  
  results = arrayUnique(results);
  
  console.log('results: ', results)

  return results;

}