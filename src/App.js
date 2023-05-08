import HeroImage from './components/HeroImage';
import Contact from './components/Contact';
import DetailContent from './components/DetailContent';
import HomeContent from './components/HomeContent';

import './App.css';


function App() {
  return (
    <div className="App">
        
      {/* <Contact/> */}
      <div className='HeroImage'><HeroImage/></div>      
      <section>
        <div className='side'>
          <div className='Agenda'>
            <h2>Agenda</h2>
            <div className='Agenda_item'>1</div>
            <div className='Agenda_item'>2</div>
            <div className='Agenda_item'>3</div>
            <div className='Agenda_item'>4</div>
            <div className='Agenda_item'>5</div>
          </div>
          {/* <Agenda /> */}
          <div className='Login'>
            <h2>Login</h2>
            </div>
          {/* <Login /> */}
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
