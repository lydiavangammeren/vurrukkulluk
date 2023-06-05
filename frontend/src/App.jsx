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
import "./assets/styles/main.css";
import { useDatabase } from "./hooks"
import { ContextProvider } from "./contexts";

function App() {
  const [recipes, isLoaded] = useDatabase('details');
  const [images, setImages] = useState([]);



  const renderContent = () => {
    if(isLoaded){
      // console.log('Data fresh from database:')
      // console.log(recipes)
      return (
        <ContextProvider recipes={recipes} >
        <div className="App">
        <Header data={images} />
        <section>
          <div className="side">
            <Agenda />
            <Login />
          </div>
          <div className="pageContainer">
          <Routes>
            <Route path="/" element={<HomeContent setImages={setImages}/>} />
            <Route
              path="/details/:slug"
              element={<DetailContent setImages={setImages} />}
            />
            <Route path="/shoppingcart" element={<ShoppingCart />} />
            <Route path="/addrecipe" element={<AddRecipePage />} />
          </Routes>
          </div>
        </section>
        <Footer />
        </div>
        </ContextProvider>
      )
    }
  }

  return (
    
      renderContent()
    
  );
}

export default App;
