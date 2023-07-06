import React, {useState, useRef} from 'react'
import validate from 'validate.js';
import { constraints } from '../../constraints/register';
import "./RegisterPage.css"

const RegisterPage = () => {
  const [inputs, setInputs] = useState({
    name: '',
    email: '',
    password: '',
    confirmPassword: ''
  })

  const [errors, setErrors] = useState({});

  const formRef = useRef();
  const form = document.querySelector(".registerForm")

  const handleChange = (event) => {
    const name = event.target.name;
    const value = event.target.value;
    setInputs(values => ({...values, [name]: value}))
  }

  const validateInput = (e) => {
    const name = e.target.name;
    console.log('inputs: ', inputs)
    const validationErrors = validate(inputs, constraints);
    console.log(validationErrors)
    if(validationErrors[name]){
      setErrors(values => ({...values, [name]: validationErrors[name]}))
    } else{
      setErrors(values => ({...values, [name]: ""}))
    }
  }

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log('form: ', form)
    console.log('ref: ', formRef)
    console.log('inputs: ', inputs)

    const test = {
      naam: '',
      email: '',
      wachtwoord: '',
      herhaalWachtwoord: ''
    }
    var formErrors = validate(test, constraints)
    setErrors(formErrors)
  };

  return (
    <div className='registerContainer'>
      <h2 className='registerheader'>Account aanmaken</h2>
      <form className='registerForm' onSubmit={handleSubmit} ref={formRef}>
        <div className='form-group'>
          <label htmlFor="name">Naam:</label>
          <input
            type="text"
            id="name"
            name="name"
            onChange={handleChange}
            value={inputs.name}
            onBlur={(e) => validateInput(e)}
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
          />
          {errors.email && errors.email.map((error) => {
              return <li className="error-message">{error}</li>
            })
          }
        </div>
        <div className='form-group'>
          <label htmlFor="password">wachtwoord:</label>
          <input
            type="password"
            id="password"
            name='password'
            onChange={handleChange}
            value={inputs.password}
            onBlur={(e) => validateInput(e)}
          />
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
