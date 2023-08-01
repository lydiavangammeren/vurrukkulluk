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
    if(!query) return [];
    const filtered = documents.filter((article)=> {
      return (
        query && (
          article.name.toLowerCase().includes(query)
        )
      )
    });
    return filtered;
}

export function search(query, documents, articleunits){

  console.log('searchValue: ', query)
  if(!query) return [];
  // const searchValue = query.trim();

  // Split the search query into separate words
  const queryWords = query.trim().toLowerCase().split(' ');

  var results = [];

  results = filter(query, documents);

  queryWords.forEach((word)=> {
    const wordResults = documents.filter((doc) => doc.name.toLowerCase().includes(word));
    // console.log('wordResults: ', wordResults)
    results = [...results, ...wordResults];
    // results = wordResults.filter(val => !results.includes(val));
  })
  
  results = arrayUnique(results);
  
  
  // add articleunits to the articles
  results.forEach(result => {
    result.articleunits = articleunits.filter((articleunit) => {
      return articleunit.article.id === result.id;
    })
  });
  
  console.log('articleunits: ', articleunits)
  console.log('results: ', results)

  return results;

}