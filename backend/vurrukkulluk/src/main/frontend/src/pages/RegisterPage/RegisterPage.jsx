import React, {useState, useRef, useEffect} from 'react'
import validate from 'validate.js';
import { constraints } from '../../constraints/register';
import "./RegisterPage.css"
import useDatabase from "../../hooks/useDatabase";
import api from "../../lib/recipeAPI"
import usePostData from '../../hooks/usePostData';
import jwtDecode from 'jwt-decode';
import { useNavigate } from 'react-router-dom';
import { FaInfo, FaInfoCircle } from "react-icons/fa";

// const emailConstraints = {
//   email: {
//     alreadyExists: true
//   }
//   , success = alert.bind(this, "The validations passed")
//   , error = function(errors) {
//       alert(JSON.stringify(errors, null, 2));
//   }
// }




validate.validators.alreadyExists = function(value) {
  return new Promise(function(resolve,reject) {
    // console.log('value: ', value)
    api.get(`/users/email/${value}`)
      .then(function (response){
        // console.log('succes getEmail: ', response);
        resolve("bestaat al");
      })
      .catch(function (error) {
        // console.log('error getEmail: ', error);
        resolve()
      })
      .finally(function () {
        // console.log('finally')
      })
    })
};

// var emailConstraints = {
//       email: {
//         presence: {
//           allowEmpty: false,
//           message: "mag niet leeg zijn"
//         },
//         email: {
//           message: "^'%{value}' is geen valide email adres"
//         },
//         alreadyExists: true
//       }
// };

const RegisterPage = () => {
  const navigate = useNavigate();
  const [data, isLoaded, postData] = usePostData();

  const [inputs, setInputs] = useState({
    name: '',
    email: '',
    password: '',
    confirmPassword: ''
  })

  const [errors, setErrors] = useState({});
  // const [infoBlock, setInfoBlock] = useState(false);
  const infoRef = useRef();

  const handleChange = (event) => {
    const name = event.target.name;
    const value = event.target.value;
    setInputs(values => ({...values, [name]: value}))
  }

  const validateInput = async (e) => {
    const name = e.target.name;

    try {
      let response = await validate.async(inputs, constraints);
      // console.log('validation Succes: ', response)
      setErrors({})
      // console.log('errors state on succes: ', errors)
    } catch(err){
      // console.log('validation Errors: ', err)
      // setErrors(err)
      if(err[name]){
        setErrors(values => ({...values, [name]: err[name]}))
      } else{
        setErrors(values => ({...values, [name]: ""}))
      }
    }
  }

  const goToLogin = () => {
    let loginEmail = document.querySelector("#login_email");
    let loginPassword = document.querySelector("#login_password")
    loginEmail.value = inputs.email;
    loginPassword.focus();
  }
 
  const handleSubmit = async (e) => {
    e.preventDefault();
    console.log('submit event: ', e)

    try {
      let response = await validate.async(inputs, constraints);
      // console.log('validation Succes: ', response)
      const body = {
        name: response.name,
        email: response.email,
        password: response.password
      }
      postData('/auth/register', body)
      // console.log('errors state on succes: ', errors)
    } catch(err){
      // console.log('validation Errors: ', err)
      setErrors(err)
    }
  };

  useEffect(()=>{
    if(!isLoaded) return;
    console.log('Register data status: ', data.status)

    switch(data.status){
    case 200:
        const tokenData = jwtDecode(data.payLoad.token)
        const user = {
            token: data.payLoad.token,
            id: tokenData.userId,
            email: tokenData.sub
        }
        console.log('Register success ' , user)
        localStorage.setItem('user', JSON.stringify(user));
        navigate("/");
        alert('Welkom ' + inputs.name)
        break;
    case 403:
        console.log('Register incorrect')
        break;
    default:
        console.log('Iets anders ' , data.status)
        console.log('Payload ' , data.payLoad)
    }

},[data, isLoaded])

  return (
    <div className='registerContainer'>
      <h2 className='registerheader'>Account aanmaken</h2>
      <form className='registerForm' onSubmit={handleSubmit} >
        <div className='form-group'>
          <label htmlFor="name">Naam:</label>
          <input
            type="text"
            id="name"
            name="name"
            onChange={handleChange}
            value={inputs.name}
            onBlur={(e) => validateInput(e)}
            // onBlur={(e)=> testValidate(e)}
          />
          {errors.name && errors.name.map((error) => {
              return <li className="error-message">{error}</li>
            })
          }
        </div>
        <div className='form-group'>
          <label htmlFor="email">Email:</label>
          <input
            type="email"
            id="email"
            name="email"
            onChange={handleChange}
            value={inputs.email}
            onBlur={(e) => validateInput(e)}
            // onBlur={(e) => validateEmail(e)}
            // onBlur={(e)=> testValidate(e)}
          />
          {errors.email && errors.email.map((error, index) => {
            if(error === "Email bestaat al"){
              return <li className="error-message" key={index}>{error} <a href="#login" onClick={()=>goToLogin()}>Log in</a></li>
            }
              return <li className="error-message" key={index}>{error}</li>
            })
          }
        </div>
        <div className='form-group'>
          <label htmlFor="password">
            Wachtwoord: 
            {/* <FaInfoCircle onMouseOver={() => setInfoBlock(true)} onMouseLeave={() => setInfoBlock(false)}/> */}
            <FaInfoCircle onMouseOver={() => infoRef.current.show()} onMouseOut={() => infoRef.current.close()} />
          </label>
          <input
            type="password"
            id="password"
            name='password'
            onChange={handleChange}
            value={inputs.password}
            onBlur={(e) => validateInput(e)}
            // onBlur={(e)=> testValidate(e)}
          />
          {<dialog ref={infoRef} className='infoBlock'>
            <p>
              <FaInfoCircle />
              8 tot 32 karakters.<br />
              Moet een hoofdletter, een kleine letter,<br />
              een cijfer en een speciaal karakter bevatten.
            </p>
          </dialog>}
          {errors.password && errors.password.map((error) => {
              return <li className="error-message">{error}</li>
            })
          }
        </div>
        <div className='form-group'>
          <label htmlFor="confirmPassword">Herhaal wachtwoord:</label>
          <input
            type="password"
            id="confirmPassword"
            name="confirmPassword"
            onChange={handleChange}
            value={inputs.confirmPassword}
            onBlur={(e) => validateInput(e)}
            // onBlur={(e)=> testValidate(e)}
          />
          {errors.confirmPassword && errors.confirmPassword.map((error) => {
              return <li className="error-message">{error}</li>
            })
          }
        </div>
        <div className='registerButtonBar'>
          <button className='registerButton' type="submit">Registreer</button>
        </div>
      </form>
    </div>
  );
};

export default RegisterPage
