import { BrowserRouter, Routes, Route } from "react-router-dom";
import HeroImage from "./components/HeroImage";
import Contact from "./components/Contact";
import Agenda from "./components/Agenda";
import DetailContent from "./components/DetailContent";
import HomeContent from "./components/HomeContent";
import Login from "./components/Login";

import "./App.css";

function App() {
  return (
    <div className="App">
      <HeroImage />
      <section>
        <div className="side">
          <Agenda />
          <Login />
        </div>
        <div className="content">
          {/* <BrowserRouter> */}
          <Routes>
            <Route index element={<HomeContent />} />
            <Route path="details" element={<DetailContent />} />
          </Routes>
          {/* </BrowserRouter> */}
        </div>
      </section>
      <Contact />
    </div>
  );
}

export default App;
