import { useState, useEffect } from "react";
import { Routes, Route } from "react-router-dom";
import Header from "./layouts/Header";
import Agenda from "./layouts/Agenda";
import Login from "./layouts/Login";
import Footer from "./layouts/Footer";
import DetailContent from "./pages/DetailPage";
import HomeContent from "./pages/HomePage";

import "./assets/styles/main.css";

import api from "./lib/recipeAPI";

function App() {
  const [recipes, setRecipes] = useState([]);
  // const [ search, setSearch ] = useState('');
  const [ images, setImages ] = useState([]);


  useEffect(() => {
    const getData = async () => {
      try{
        const response = await api.get('/recipes');
        setRecipes(response.data);
        // console.log('recipes set');
      } catch(err){
        if(err.response){
          //Not in the 200 response range
          console.log(err.response.data);
          console.log(err.response.status);
          console.log(err.response.headers);
        }else{
          console.log(`Error: ${err.message}`)
        }
      }
    }
    getData();
  }, [])

  useEffect(() => {
    let items = [];
    recipes.map((recipe, index) => {
      return items.push({src:recipe.image, alt:recipe.image})
    })
    // console.log(items);
    setImages(items);
  }, [recipes])


  return (
    <div className="App">
      <Header data={images}/>
      <section>
        <div className="side">
          <Agenda />
          <Login />
        </div>
        <div className='pageContainer'>
          <Routes>
            <Route path="/" element={<HomeContent recipes={recipes}/>}/>
            <Route path="/details/:id" element={<DetailContent />}/>
          </Routes>
        </div>
      </section>
      <Footer />
    </div>
  );
}

export default App;
