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
import { RecipesProvider } from "./contexts/RecipesContext";

function App() {
  const [recipes, setRecipes] = useState([]);
  const [images, setImages] = useState([]);

  return (
    <div className="App">
    <RecipesProvider >
      <Header data={images} setRecipes={setRecipes} /> {/* Deze werkt niet met BACKEND API */}
      <section>
        <div className="side">
          <Agenda />
          <Login />
        </div>
        <div className="pageContainer">
          <Routes>
            <Route path="/" element={<HomeContent setImages={setImages} recipes={recipes} setRecipes={setRecipes}/>} />
            <Route
              path="/details/:slug"
              element={<DetailContent setImages={setImages} />}
            />
            <Route path="/shoppingcart" element={<ShoppingCart />} />
            <Route path="/addrecipe" element={<AddRecipePage />} />
          </Routes>
        </div>
      </section>
    </RecipesProvider>
      <Footer />
    </div>
  );
}

export default App;
