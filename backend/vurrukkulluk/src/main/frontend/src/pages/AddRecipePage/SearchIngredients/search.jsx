

export function search(query, documents){

  // const searchValue = query.trim();

  // Split the search query into separate words
  const queryWords = query.trim().toLowerCase().split(' ');

  var results = [];

  queryWords.forEach((word)=> {
    const wordResults = documents.filter((doc) => doc.name.toLowerCase().includes(word));
    console.log('wordResults: ', wordResults)
    // results = [...results, ...wordResults];
    results = wordResults.filter(val => !results.includes(val));
  })

  console.log('results: ', results)

  return results;

}