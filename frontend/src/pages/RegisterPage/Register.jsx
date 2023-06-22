import React, { useEffect, useState } from 'react'
import { useNavigate } from 'react-router-dom';
import './Register.css';
import usePostData from '../../hooks/usePostData';
import jwtDecode from 'jwt-decode';

const Register = () => {
    const [inputs, setInputs] = useState({
        name: '',
        email: '',
        firstpassword: '',
        secondpassword: ''
    })

    const [data, isLoaded, postData] = usePostData();

    const navigate = useNavigate();

    const handleChange = (event) => {
        const name = event.target.name;
        const value = event.target.value;
        setInputs(values => ({...values, [name]: value}))
    }

    const handleSubmit = (event) => {
        event.preventDefault();
        // console.log(inputs);

        const body = {
            name: inputs.name,
            email: inputs.email,
            password: inputs.firstpassword
        }

        console.log('Body: ', body)
        postData('/auth/register', body)


    }

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
            break;
        case 403:
            console.log('Register incorrect')
            break;
        default:
            console.log('Iets anders ' , data.status)
            console.log('Payload ' , data.payLoad)
        }

    },[data, isLoaded])


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
                                name="name"
                                value={inputs.name}
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
                                name="email"
                                value={inputs.email}
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
                                value={inputs.firstpassword}
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
                                value={inputs.secondpassword}
                                onChange={handleChange}
                            />
                    </div>
                </div>
                <br/><br/>

                </div>
                <button type="submit" className='registerbutton'>Registreren</button>
            </form>
        </div>
    )
}

export default Register