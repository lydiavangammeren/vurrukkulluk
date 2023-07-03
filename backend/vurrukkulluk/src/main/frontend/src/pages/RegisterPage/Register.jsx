import React, { useEffect, useState } from 'react'
import { useNavigate } from 'react-router-dom';
import './Register.css';
import usePostData from '../../hooks/usePostData';
import jwtDecode from 'jwt-decode';
import {BsCheckSquare, BsXSquare} from "react-icons/bs";

const USER_REGEX = /^[A-z][A-z0-9-_]{1,23}$/;
const EMAIL_REGEX = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
const PWD_REGEX = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%]).{8,32}$/;

const Register = () => {
    const [inputs, setInputs] = useState({
        name: '',
        email: '',
        firstpassword: '',
        secondpassword: ''
    })

    const [validName, setValidName] = useState(false);
    const [validEmail, setValidEmail] = useState(false);
    const [validPwd, setValidPwd] = useState(false);
    const [validMatch, setValidMatch] = useState(false);

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
        const v1 = "";


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

    useEffect(() => {
        const check = USER_REGEX.test(inputs.name);
        setValidName(check);
    }, [inputs.name])

    useEffect(() => {
        const check = EMAIL_REGEX.test(inputs.email);
        setValidEmail(check);
    }, [inputs.email])

    useEffect(() => {
        setValidPwd(PWD_REGEX.test(inputs.firstpassword));
        setValidMatch(inputs.firstpassword === inputs.secondpassword);
    }, [inputs.firstpassword, inputs.secondpassword])

    const canSubmit = validName && validEmail && validPwd && validMatch;

    return(
        <div className='registercontainer'>
            <h2 className='registerheader'>Account aanmaken</h2>
            <form onSubmit={handleSubmit}>
            <div className='registerformcontainer'>
                <div className='inputs'>
                    <div className='username'>
                        <label className='registerlabels'>
                            Vul hier uw naam in:  
                            <BsCheckSquare color='green' className={validName? "valid" : "hide"}/>
                            <BsXSquare color='red' className={validName || !inputs.name? "hide" : "invalid"}/>
                        </label>
                        <br/>
                        <input
                            className='registerfields'
                            type="text"
                            name="name"
                            value={inputs.name}
                            onChange={handleChange}
                            autoFocus
                        />
                    </div>
                <br/>
                    <div className='email'>
                        <label className='registerlabels'>
                            Vul hier uw e-mailadres in:
                            <BsCheckSquare color='green' className={validEmail? "valid" : "hide"}/>
                            <BsXSquare color='red' className={validEmail || !inputs.email? "hide" : "invalid"}/>
                        </label>
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
                        <label className='registerlabels'>
                            Vul hier uw wachtwoord in:
                            <BsCheckSquare color='green' className={validPwd? "valid" : "hide"}/>
                            <BsXSquare color='red' className={validPwd || !inputs.firstpassword? "hide" : "invalid"}/>
                        </label>
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
                        <label className='registerlabels'>
                            Herhaal uw wachtwoord:
                            <BsCheckSquare color='green' className={validMatch && inputs.secondpassword? "valid" : "hide"}/>
                            <BsXSquare color='red' className={validMatch || !inputs.secondpassword? "hide" : "invalid"}/>
                        </label>
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
                <button type="submit" className='registerbutton' disabled={!canSubmit}>Registreren</button>
            </form>
        </div>
    )
}

export default Register