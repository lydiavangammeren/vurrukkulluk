import { Routes, Route } from "react-router-dom";
import DetailContent from "./pages/DetailPage";
import HomeContent from "./pages/HomePage";
import ShoppingCart from "./pages/ShoppingCartPage/ShoppingCart";
import AddRecipe from "./pages/AddRecipePage";
// import Register from "./pages/RegisterPage/Register";
import RegisterPage from "./pages/RegisterPage/RegisterPage";
import "./assets/styles/main.css";
import { useDatabase } from "./hooks"
import { ContextProvider, ShopContextProvider } from "./contexts";
import Layout from "./layouts";
// import ImageResize from "./pages/testPage/ImageResize";
// import ImgDropAndCrop from "./pages/testPage/ImgDropAndCrop";
// import CropDemo from "./pages/testPage/CropDemo";
// import ObjectToArray from "./pages/testPage/ObjectToArray";
import ScrollToTop from "./components/ScrollToTop";
import Favorites from "./pages/Favorites/Favorites";
import ResetPassword from "./pages/ResetPassword/ResetPassword";

function App() {
  const [recipes, isLoaded, keepRecipeCache] = useDatabase('recipes'); // Get all data from database (Recipes+ingredients+preparation+comments)
  
  const getImages = () => {
    if(!isLoaded) return [];
    const shuffled = [...recipes].sort(() => 0.5 - Math.random());
    return shuffled.slice(0,5).map(obj => obj.imageId);
  }

  const images = getImages();

  const renderContent = () => {
    if(isLoaded){
      return (
        <ContextProvider recipes={recipes} bannerImages={images} keepRecipeCache={keepRecipeCache}>
          <ShopContextProvider >
          <ScrollToTop />
          <Layout >
            <Routes>
              <Route path="/" element={<HomeContent />} />
              <Route
                path="/details/:slug"
                element={<DetailContent />}
              />
              <Route path="/shoppingcart" element={<ShoppingCart />} />
              <Route path="/addrecipe" element={<AddRecipe />} />
              <Route path="/register" element={<RegisterPage />} />
              <Route path="/favorites" element={<Favorites />} />
              <Route path="/resetpassword" element={<ResetPassword />} />
              {/* <Route path="/test" element={<ObjectToArray />} /> */}
            </Routes>
          </Layout>
          </ShopContextProvider>
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
