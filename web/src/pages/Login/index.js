import React, {useState} from 'react';
import { useHistory } from 'react-router-dom';

import api from '../../services/api';
import { doLogin, doLogout } from '../../services/auth';

import './styles.css';

var CryptoJS = require("crypto-js");

export default function Login(){

    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [isLoading, setIsLoading] = useState(false);
    const history = useHistory();

    async function handelLogin(e){
        e.preventDefault();

        setIsLoading(true);

        const encryptedPassword = CryptoJS.AES.encrypt(password, "segredoJava").toString();

        console.log(encryptedPassword);
        // U2FsdGVkX1897nQ5iQ3wHbKrE9DE784eWKEVxMLS3b8=
        // qnu5Q+rl2471BIyp/6w47w==
        // admin123

        const credentials = {username, password: encryptedPassword};

        await api.post('/auth/signin', credentials)
          .then((response) => {
            console.log(response.data);
            const {token} = response.data;
            doLogin(token);
            history.push("/books");
          })
          .catch((error) => {
            console.log(error);
            doLogout();
            alert('Algo deu errado. ',error);
          })
          .finally(() => setIsLoading(false));

    }

    return(
        <>
            <div className='h1-centro' >
              { isLoading ?  (
                  <h1>Enviando</h1>
                ) : (
                  <h1>Sistema API Rest com Springboot</h1>
                ) }
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