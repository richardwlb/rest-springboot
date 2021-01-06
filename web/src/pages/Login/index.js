import React, {useState} from 'react';

import api from '../../services/api';

import './styles.css';

export default function Login(){

    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    async function handelLogin(e){
        e.preventDefault();

        const response = await api.post('/auth/signin', {username, password});

        console.log(response);
    }

    return(
        <>
            <div className='h1-centro' >
                <h1>Sistema API Rest com Springboot</h1>
            </div>
            <div className="login">
                <form onSubmit={handelLogin}>
                    <label htmlFor='username' >Login
                    <input 
                        name='username' 
                        type="text" 
                        placeholder='seu usuário'
                        value={username}
                        onChange={ e => setUsername(e.target.value)}
                    />
                    </label>
                    <label htmlFor='password' >Senha
                    <input 
                        name='password' 
                        type="password" 
                        placeholder='sua senha'
                        value={password}
                        onChange={ e => setPassword(e.target.value)}
                    />
                    </label>
                    <button type="submit">Entrar</button>
                </form>
            </div>
        </>
    );
}