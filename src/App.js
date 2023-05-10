import { Routes, Route } from "react-router-dom";
import HeroImage from './components/HeroImage';
import Contact from './components/Contact';
import Agenda from './components/Agenda';
import DetailContent from './components/DetailContent';
import HomeContent from './components/HomeContent';
import Login from './components/Login';

import './App.css';


function App() {
  return (
    <div className="App">
      <div className='HeroImage'><HeroImage/></div>      
      <section>
        <div className='side'>
          <Agenda />
          <div className='Login'>
            <Login/>
            </div>          
        </div>
        <div className='content'>
            <Routes>
              <Route path="/" element={<HomeContent />}/>
              <Route path="/details/:id" element={<DetailContent />}/>
            </Routes>
          
        </div>
      </section>
      <div className='Contact'><Contact/></div>      
    </div>
  );
}

export default App;
