import React, { useRef, useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import api from "../../lib/recipeAPI";
import usePostData from "../../hooks/usePostData";
import { useAppContext } from "../../contexts/AppContext";
import jwtDecode from "jwt-decode";
// import { login, logout } from "../../hooks/useAuthService";

const Login = () => {

  const navigate = useNavigate()
  // const { user, setUser } = useAppContext();

  // const [userName, setUserName] = useState('');
  // const [ pwd, setPwd ] = useState('');

  const [input, setInput] = useState({email: '', password: ''})

  const [data, isLoaded, postData] = usePostData();

  const handleChange = (e) => {
    const name = e.target.name
    const value = e.target.value

    setInput(prev => ({
      ...prev,
      [name]:value
    }))
  }

  const forgotPassword = () => {
    console.log("Send email to reset password")
  }  

  const handleSubmit = async (e) => {
    e.preventDefault();

    postData('/auth/authenticate', input)
    // const response = login(input.email, input.password);
    // console.log('Response: ', response)
    setInput({email: '', password: ''})
  }

  const logout = () => {
    localStorage.removeItem('user');
    setInput({email: '', password: ''})
  }

  useEffect(() => {
    if(!isLoaded) return;

    switch(data.status){
      case 200:
        const tokenData = jwtDecode(data.payLoad.token)
        const user = {
          token: data.payLoad.token,
          id: tokenData.userId,
          email: tokenData.sub
        }
        console.log('Inlog success ' , user)
        localStorage.setItem('user', JSON.stringify(user));
        // alert('Welkom ' + userName)
        // setUser(input.email)
        // setUserName('');
        // setPwd('');
        setInput(prev=> ({...prev, password: ''})) // Om de component te re-renderen
        break;
      case 403:
        console.log('Login incorrect')
        break;
      default:
        console.log('Iets anders ' , data.status)
        console.log('Payload ' , data.payLoad)
    }
  }, [data, isLoaded])

  const loginForm = () => {
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
            value={input.email}
            onChange={handleChange}
            />
          <br/><br/>
          <label className="label" htmlFor="password">Wachtwoord </label>  
          <br/>        
          <input 
            name="password" 
            id="password" 
            type="password" 
            value={input.password}
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
    )
  }

  const userDisplay = () => {
    console.log("localStorage: " , JSON.parse(localStorage.getItem('user')).email)
    return (
      <div className="Login">
        <h1>Welkom</h1>
        <h2>{JSON.parse(localStorage.getItem('user')).email}</h2>
        <button onClick={logout}>Log uit</button>
      </div>
    )
  }


  return (
    localStorage.getItem('user') === null ?
    loginForm() : userDisplay()
  );
};

export default Login;
