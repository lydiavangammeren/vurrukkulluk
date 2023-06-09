import React, { useState } from 'react'
import './Register.css';

const Register = () => {
    const [inputs, setInputs] = useState({})

    const handleChange = (event) => {
        const name = event.target.name;
        const value = event.target.value;
        setInputs(values => ({...values, [name]: value}))
    }

    const handleSubmit = (event) => {
        event.preventDefault();
        console.log(inputs);
    }


    return(
        <div className='registercontainer'>
            <h2 className='registerheader'>Account aanmaken</h2>
            <form onSubmit={handleSubmit}>
            <div className='registerformcontainer'>
                <div className='inputs'>
                    <div className='username'>
                        <label className='registerlabels'>Vul hier uw naam in:</label>
                            <br/>
                            <input
                                className='registerfields'
                                type="text"
                                name="username"
                                value={inputs.username || ''}
                                onChange={handleChange}
                            />
                    </div>
                <br/>
                    <div className='email'>
                        <label className='registerlabels'>Vul hier uw e-mailadres in:</label>
                            <br/>
                            <input
                                className='registerfields'
                                type="email"
                                name="emailaddress"
                                value={inputs.emailaddress || ''}
                                onChange={handleChange}
                            />
                    </div>
                <br/>
                    <div className='password'>
                        <label className='registerlabels'>Vul hier uw wachtwoord in:</label>
                            <br/>
                            <input
                                className='registerfields'
                                type="password"
                                name="firstpassword"
                                value={inputs.firstpassword || ''}
                                onChange={handleChange}
                            />
                    </div>
                <br />
                    <div className='secondpassword'>
                        <label className='registerlabels'>Herhaal uw wachtwoord:</label>
                            <br />
                            <input
                                className='registerfields'
                                type="password"
                                name="secondpassword"
                                value={inputs.secondpassword || ''}
                                onChange={handleChange}
                            />
                    </div>
                </div>
                <br/><br/>

                </div>
                <input type="submit" value="Registreren" className='registerbutton' />
            </form>
        </div>
    )
}

export default Register