import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

const LoginForm = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState(null);
  const navigate = useNavigate();

  const handleLogin = async (e) => {
    e.preventDefault(); // Prevent the default form submission

    try {
      const response = await fetch('http://localhost:8080/user/login', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({username, password}),
      });

      if (response.ok) {
        navigate('/home');
      } else {
        const errorResponse = await response.json(); // Assuming the server sends error details as JSON
        setError(errorResponse.message || 'Login failed');
        console.error('Login failed:', errorResponse);
      }
    } catch (error) {
      setError('Error during login');
      console.error('Error during login:', error);
    }
  };

  return (
    <div className="text-center" style={{ margin: '150px' }}>
      <form onSubmit={handleLogin}>
        <label style={{ margin: '10px' }}>Username</label>
        <input type="text" value={username} onChange={(e) => setUsername(e.target.value)} />
        <div> </div>
        <label style={{ margin: '10px' }}>Password</label>
        <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} />
        <div> </div>
        <button type="submit">Submit</button>
      </form>
      {error && (
        <div style={{ border: '1px solid red', padding: '10px', margin: '10px 0', color: 'red' }}>
          {error}
        </div>
      )}
    </div>
  );
};

export default LoginForm;
