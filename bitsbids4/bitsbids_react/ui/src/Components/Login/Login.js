import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

const LoginForm = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState(null);
  const [isLoading, setIsLoading] = useState(false); // Added loading state
  const navigate = useNavigate();

  const handleLogin = async (e) => {
    e.preventDefault();

    try {
      setIsLoading(true); // Set loading state when login starts

      const response = await fetch('http://localhost:8081/user/login', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ username, password }),
      });

      if (response.ok) {
        navigate(`/user/${username}`);
      } else {
        const errorResponse = await response.json();
        setError(errorResponse.message || 'Login failed');
        console.error('Login failed:', errorResponse);
      }
    } catch (error) {
      setError('Error during login');
      console.error('Error during login:', error);
    } finally {
      setIsLoading(false); // Reset loading state regardless of success or failure
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
        <button type="submit" disabled={isLoading}>
          {isLoading ? 'Logging in...' : 'Submit'}
        </button>
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
