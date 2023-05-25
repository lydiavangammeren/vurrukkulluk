import { useState } from "react";
import { Routes, Route } from "react-router-dom";
import Header from "./layouts/Header";
import Agenda from "./layouts/Agenda";
import Login from "./layouts/Login";
import Footer from "./layouts/Footer";
import DetailContent from "./pages/DetailPage";
import HomeContent from "./pages/HomePage";
import Shoppingcarts from "./pages/Shoppingcart/ShoppingCarts";
import AddRecipePage from "./pages/AddRecipe/AddRecipePage";
import "./assets/styles/main.css";

function App() {
  const [images, setImages] = useState([]);

  return (
    <div className="App">
      <Header data={images} />
      <section>
        <div className="side">
          <Agenda />
          <Login />
        </div>
        <div className="pageContainer">
          <Routes>
            <Route path="/" element={<HomeContent setImages={setImages} />} />
            <Route
              path="/details/:id"
              element={<DetailContent setImages={setImages} />}
            />
            <Route path="/shoppingcart" element={<Shoppingcarts />} />
            <Route path="/addrecipe" element={<AddRecipePage />} />
          </Routes>
        </div>
      </section>
      <Footer />
    </div>
  );
}

export default App;
