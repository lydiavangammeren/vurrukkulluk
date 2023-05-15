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
          <br />
          <label className="label" htmlFor="wachtwoord">Wachtwoord </label>  
          <br/>        
          <input name="wachtwoord" id="wachtwoord" type="password" />
          <br />
          <input id="button" type="submit" value="Login" />
        </form>
      </div>
    </div>
  );
};

export default Login;
