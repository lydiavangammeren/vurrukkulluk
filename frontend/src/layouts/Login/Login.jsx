import React, { useRef, useState } from "react";
import { useNavigate } from "react-router-dom";
import api from "../../lib/recipeAPI";

const Login = () => {

  const navigate = useNavigate()
  // const [credentials, setCredentials] = useState({email: '', password: ''});
  // const userRef = useRef();
  // const errRef = useRef();

  const [user, setUser] = useState('');
  const [ pwd, setPwd ] = useState('');
  const [ errMsg, setErrMsg ] = useState('');


  const forgotPassword = () => {
    console.log("Send email to reset password")
  }  

  // const handleChange = e => {
  //   const name = e.target.name
  //   const value = e.target.value

  //   setCredentials(prev => ({
  //     ...prev,
  //     [name]: value
  //   }))
  // }

  const handleSubmit = async (e) => {
    e.preventDefault();
    console.log('Welkom ' + user)
    
    try{
      const response = await api.post('/auth/authenticate', 
          JSON.stringify({user, pwd}),
          {
            headers: {'Content-Type': 'application/json' },
            withCredentials: true
          }
          );
          console.log(JSON.stringify(response?.data));

          setUser('');
          setPwd('');

    } catch(err) {
      console.log('Error in authenticate: ')
      console.log(err?.response);
      // if (!err?.response) {
      //   setErrMsg('No Server Response');
      // } else if (err.response?.status === 400) {
      //     setErrMsg('Missing Username or Password');
      // } else if (err.response?.status === 401) {
      //     setErrMsg('Unauthorized');
      // } else {
      //     setErrMsg('Login Failed');
      // }
    }
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
            // ref={userRef}
            value={user}
            onChange={(e)=>{setUser(e.target.value)}}
            />
          <br/><br/>
          <label className="label" htmlFor="password">Wachtwoord </label>  
          <br/>        
          <input 
            name="password" 
            id="password" 
            type="password" 
            value={pwd}
            onChange={(e)=>{setPwd(e.target.value)}}
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
