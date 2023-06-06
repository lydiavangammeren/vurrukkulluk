import React, { useState } from 'react'

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
        <div className='registerForm'>
            <form onSubmit={handleSubmit}>
                <label>Vul hier uw naam in:
                    <br/>
                    <input
                        type="text"
                        name="username"
                        value={inputs.username || ''}
                        onChange={handleChange}
                    />
                </label>
                <br/>
                <label>Vul hier uw e-mailadres in:
                    <br/>
                    <input
                        type="email"
                        name="emailaddress"
                        value={inputs.emailaddress || ''}
                        onChange={handleChange}
                    />
                </label>
                <br/>
                <label>Vul hier uw wachtwoord in:
                    <br/>
                    <input
                        type="password"
                        name="firstpassword"
                        value={inputs.firstpassword || ''}
                        onChange={handleChange}
                    />
                </label>
                <br />
                <label>Herhaal uw wachtwoord:
                    <br />
                    <input
                        type="password"
                        name="secondpassword"
                        value={inputs.secondpassword || ''}
                        onChange={handleChange}
                    />
                </label>
                <br/><br/>
                <input type="submit" value="Registreren" />
            </form>
        </div>
    )
}

export default Register