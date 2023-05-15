import { Routes, Route } from "react-router-dom";
import Header from "./layouts/Header";
import Agenda from "./layouts/Agenda";
import Login from "./layouts/Login";
import Footer from "./layouts/Footer";
import DetailContent from "./pages/DetailPage";
import HomeContent from "./pages/HomePage";

import "./assets/styles/main.css";

function App() {
  return (
    <div className="App">
      <Header />
      <section>
        <div className="side">
          <Agenda />
          <Login />
        </div>
        <div className='pageContainer'>
          <Routes>
            <Route path="/" element={<HomeContent />}/>
            <Route path="/details/:id" element={<DetailContent />}/>
          </Routes>
        </div>
      </section>
      <Footer />
    </div>
  );
}

export default App;
