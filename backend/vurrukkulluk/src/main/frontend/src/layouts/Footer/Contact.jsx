import React from "react";

const Contact = () => {
  return (
    <div className="contact">
      <div className="contact-image">
      <img src={"/images/logo-zonder-verrukkeluk.png"} 
      alt="vurrukkulluk logo zonder tekst" 
      width="100" height="auto"/>
      </div>
      <div className="contact-address">
        <h1>Contact</h1>
        <p>Verrukkulluk.nl</p>        
        <p>Poststraat 2b</p>
        <p>Sittard</p>
        <p>info@vurrukkulluk.nl</p>
      </div>
    </div>
    
  );
};

export default Contact;
