import HeroImage from './components/HeroImage';
import Contact from './components/Contact';
import Login from './components/Login';

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
            <Login/>
            </div>          
        </div>
        <div className='content'>
          {/* <h1>Content</h1> */}
         
        </div>
      </section>
      <div className='Contact'><Contact/></div>      
    </div>
  );
}

export default App;
