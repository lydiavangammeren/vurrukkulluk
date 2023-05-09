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
        
      {/* <Contact/> */}
      <div className='HeroImage'><HeroImage/></div>      
      <section>
        <div className='side'>
          
          <Agenda />
          <div className='Login'>
            <Login/>
            </div>          
        </div>
        <div className='content'>
          {/* <h1>Content</h1> */}
          <HomeContent />
        </div>
      </section>
      <div className='Contact'><Contact/></div>      
    </div>
  );
}

export default App;
