import { useState } from "react";
import { Routes, Route } from "react-router-dom";
import Header from "./layouts/Header";
import Agenda from "./layouts/Agenda";
import Login from "./layouts/Login";
import Footer from "./layouts/Footer";
import DetailContent from "./pages/DetailPage";
import HomeContent from "./pages/HomePage";
import ShoppingCart from "./pages/ShoppingCartPage/ShoppingCart";
import AddRecipePage from "./pages/AddRecipe/AddRecipePage";
import Register from "./pages/RegisterPage/Register";
import "./assets/styles/main.css";
import { useDatabase } from "./hooks"
import { ContextProvider } from "./contexts";

function App() {
  const [recipes, isLoaded] = useDatabase('recipes'); // Get all data from database (Recipes+ingredients+preparation+comments)
  // const [images, imagesLoaded] = useDatabase('image/1');
  
  const getImages = () => {
    if(!isLoaded) return [];
    const shuffled = [...recipes].sort(() => 0.5 - Math.random());
    return shuffled.slice(0,5).map(obj => obj.imageId);
  }

  // const [images, setImages] = useState();


  const images = getImages();

  const renderContent = () => {
    if(isLoaded){
      return (
        <ContextProvider recipes={recipes} images={images}>
        <div className="App">
        <Header />
        <section>
          <div className="side">
            <Agenda />
            <Login />
          </div>
          <div className="pageContainer">
          <Routes>
            <Route path="/" element={<HomeContent />} />
            <Route
              path="/details/:slug"
              element={<DetailContent />}
            />
            <Route path="/shoppingcart" element={<ShoppingCart />} />
            <Route path="/addrecipe" element={<AddRecipePage />} />
            <Route path="/register" element={<Register />} />
          </Routes>
          </div>
        </section>
        <Footer />
        </div>
        </ContextProvider>
      )
    } else {
      return <p>Loading recipes...</p>
    }
  }

  return (
    
      renderContent()
    
  );
}

export default App;
