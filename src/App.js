import HeroImage from './components/HeroImage';
import Contact from './components/Contact';
import SearchBar from './components/SearchBar';

import './App.css';


function App() {
  return (
    <div className="App">
      <HeroImage/>    
      <Contact/>
      <div className='HeroImage'><h1>HeroImage</h1></div>
      {/* <HeroImage/> */}
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
         
        </div>
      </section>
      <div className='Contact'><h1>Contact</h1></div>
      {/* <Contact/> */}
    </div>
  );
}

export default App;
