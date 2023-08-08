import React from 'react'
import "./ResetPassword.css"
import usePostData from '../../hooks/usePostData'

const ResetPassword = () => {
  const [data, isLoaded, postData] = usePostData();
  const handleSubmit = (e) => {
    e.preventDefault();

    const body = {
      email: '',
      password: '',
      newPassword: ''
    }

    postData('/auth/OTP/authenticate')
  }

  return (
    <div className='resetpassword'>
      <h2>Reset je wachtwoord</h2>
      <form onSubmit={handleSubmit}>
        <label htmlFor='temp_password'>Eenmalig wachtwoord</label>
        <input 
          type='text'
          id='temp_password'
          name='temp_password'
        />
        <label htmlFor='new_password'>Nieuw wachtwoord</label>
        <input 
          type='password' 
          id='new_password'
          name='new_password'
        />
        <label htmlFor='repeat_new_password'>Nieuw wachtwoord</label>
        <input 
          type='password' 
          id='repeat_new_password'
          name='repeat_new_password'
        />
      </form>
    </div>
  )
}

export default ResetPassword
