import React from "react";
import { useNavigate } from "react-router-dom";

const Login = () => {

  const navigate = useNavigate()

const Dummy = () => {
  console.log("clicked")
}  
  return (
    <div className="Login">
      <h1 className="login-header">Login</h1>
      <div className="form-container">
        <form className="form">
          <label className="label" htmlFor="email">Email </label>
          <br/>
          <input name="email" id="email" type="email" />
          <br/><br/>
          <label className="label" htmlFor="wachtwoord">Wachtwoord </label>  
          <br/>        
          <input name="wachtwoord" id="wachtwoord" type="password" />
          <div className="login-bottom">
            <button className="login-button">Login</button> 
          </div>
        </form>
          <div className="otherOptions">
            <div className="forgotpass" onClick={Dummy}>
              <button className="forgotpass">
                Wachtwoord vergeten?
              </button>
            </div>
            <div className="register">
              <button className="register" onClick = { () => navigate(`/register`)}>
                Registreren
              </button>
            </div>
          </div>
          
      </div>
    </div>
  );
};

export default Login;
