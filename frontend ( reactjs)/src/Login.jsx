import React, { useEffect, useState } from 'react';
import { getAuthToken, request, setAuthHeader } from './helpers/axios_helper';


const Login = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState(null); // Added state for error

    const handleKeyPress = (event) => {
        if (event.key === 'Enter') {
            handleSubmit();
        }
    }


    const handleSubmit = async () => {

        const loginData = {
            "username": username,
            "password": password
        }

        try {
            const response = await request("post", "/signin", loginData);
            const data = response.data;
            console.log(data.jwt);
            setAuthHeader(data.jwt);
            console.log(getAuthToken());
            location.href = "/";
        } catch (error) {
            console.error("from login composant", error);
        }
    };

    useEffect(() => {
        const query = new URLSearchParams(location.search);
        setError(query.get('error')); // Update error state with query parameter

    }, [location.search]);


    return (
        <div className="container mt-5">
            <div className="row justify-content-center">
                <div className="col-md-6">
                    <div className="card">
                        <div className="card-body">
                            {error === '1' && (
                                <div className="alert alert-danger" role="alert">
                                    Vous devez être connecté pour continuer !
                                </div>
                            )}
                            {error === '2' && (
                                <div className="alert alert-danger" role="alert">
                                    Mot de passe ou nom d'utilisateur incorrect !
                                </div>
                            )}
                            <h2 className="card-title text-center">Login</h2>
                            <p id='errormessage'></p>
                            <div id='loginForm'>
                                <div className="form-group">
                                    <label htmlFor="username">Username</label>
                                    <input
                                        type="text"
                                        className="form-control"
                                        id="username"
                                        value={username}
                                        onChange={(e) => setUsername(e.target.value)}
                                        onKeyDown={handleKeyPress} // Add onKeyDown event handler
                                        required
                                    />
                                </div>
                                <div className="form-group">
                                    <label htmlFor="password">Password</label>
                                    <input
                                        type="password"
                                        className="form-control"
                                        id="password"
                                        value={password}
                                        onChange={(e) => setPassword(e.target.value)}
                                        onKeyDown={handleKeyPress} // Add onKeyDown event handler
                                        required
                                    />
                                </div><br />
                                <div className='text-center'> <button onClick={handleSubmit} className="btn btn-primary btn-block">Login</button></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default Login;
