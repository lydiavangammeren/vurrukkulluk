import React, { useState } from "react";
import { useNavigate } from "react-router-dom";

const Login = () => {

  const navigate = useNavigate()
  const [credentials, setCredentials] = useState({email: '', password: ''});

  const forgotPassword = () => {
    console.log("Send email to reset password")
  }  

  const handleChange = e => {
    const name = e.target.name
    const value = e.target.value

    setCredentials(prev => ({
      ...prev,
      [name]: value
    }))
  }

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log('Welkom ' + credentials.email)
    console.log(JSON.stringify(credentials))
  }

  return (
    <div className="Login">
      <h1 className="login-header">Login</h1>
      <div className="form-container">
        <form className="form" onSubmit={handleSubmit}>
          <label className="label" htmlFor="email">Email </label>
          <br/>
          <input 
            name="email" 
            id="email" 
            type="email" 
            value={credentials.email}
            onChange={handleChange}
            />
          <br/><br/>
          <label className="label" htmlFor="password">Wachtwoord </label>  
          <br/>        
          <input 
            name="password" 
            id="password" 
            type="password" 
            value={credentials.password}
            onChange={handleChange}
            />
          <div className="login-bottom">
            <button className="login-button">Login</button> 
          </div>
        </form>
          <div className="otherOptions">
            <div className="forgotpass">
              <button className="forgotpass" onClick={forgotPassword}>
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
