import React from "react";

const Login = () => {

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
            <button>Login</button>
          </div>
          
        </form>
      </div>
    </div>
  );
};

export default Login;
