import React from 'react'
import Rating from './Rating';
import '../css/Details.css';
import { HiUsers } from 'react-icons/hi';
import { MdEuro } from 'react-icons/md';
import { VscFlame } from 'react-icons/vsc';


const Details = (props) => {
  return (
    <div className='details'>
      <div className='details_img'>
      <img src={require("../assets/images/" + props.recipe.image )}
        alt={props.recipe.image}
        width="100%"
        height="auto" />
      </div>
      <div className='details_info'>
        <div className='details_stats'>
        <p>
            <HiUsers color='#b31714'/> 4 
            <MdEuro color='#b31714'/>12,- 
            <VscFlame color='#b31714'/> 432
          </p>
          {/* Aantal personen  / Prijs / Calorieen */}
        </div>
        <div className='title_rating'>
          <div className='details_title'>
            <h2>Title</h2>
          </div>
          <div className='details_rating'>
            <Rating />
            {/* <Rating /> --> Rating Component */}
          </div>
        </div>
        <div className='kitchen_type'>
          <div className='details_kitchen'><span>Keuken</span></div>
          <div className='details_type'><span>Type</span></div>
        </div>
        <p>
        Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque eu metus sem. Sed lobortis tempor arcu. 
        Nulla id nulla in nibh dictum feugiat. Donec sed accumsan est, at accumsan velit. Fusce porttitor feugiat 
        lectus, sit amet gravida elit egestas ac. Sed convallis sapien quis justo elementum consectetur. Maecenas 
        tempus, turpis sed consectetur pellentesque, orci tortor consectetur nisl, sed posuere enim sem mattis 
        diam. Sed leo magna, commodo et accumsan gravida, lobortis a diam. Curabitur dignissim finibus nunc in 
        facilisis. Praesent at porta augue.</p>
        <p>
        Integer lacinia ipsum tellus, ut posuere risus consectetur in. Nullam ut elit nec eros rhoncus facilisis 
        non a mauris. Nulla arcu sapien, rhoncus vitae suscipit quis, volutpat non eros. Nam lacinia felis ante, 
        a cursus nisi varius ut. Nulla vel rhoncus mauris. Nunc lobortis volutpat leo. Integer consequat molestie 
        elementum. Cras et tempus lorem.
        </p>
        <div className='details_buttons'>
          <button>Op Lijst</button>
          <button>Favourite</button>
          {/* Op Lijst-Button      Favourite-Button */}
        </div>
      </div>
    </div>
  )
}

export default Details
