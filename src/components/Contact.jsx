import React from "react";

const Contact = () => {
  return (
    <div className="contact">
      <img src={require("../assets/images/vurrukkulluk-logo.png")} alt="vurrukkulluk logo" />
      <div className="address">
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
