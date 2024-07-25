import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

export let usernameExport = '';

const Registration = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [email, setEmail] = useState('');
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      setLoading(true);

      const response = await fetch('http://localhost:8081/user/register', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ username, password, email }),
      });

      if (!response.ok) {
        const errorMessage = await response.text();
        throw new Error(`HTTP error! Status: ${response.status}. Message: ${errorMessage}`);
      }

      console.log('New student added:');
      setError(null);
      navigate(`/user/${username}`);
      usernameExport = username;
    } catch (error) {
      console.error('Error adding new student:', error);

      if (error.message.includes('Username already exists')) {
        setError('Username already exists. Please choose another one.');
      } else if (error.message.includes('Email already exists')) {
        setError('Email already exists. Please use a different one.');
      } else {
        setError('Error submitting the form. Please try again.');
      }
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="text-center" style={{ margin: '150px' }}>
      <form onSubmit={handleSubmit}>
        <label style={{ margin: '10px' }}> Username</label>
        <input type="text" value={username} onChange={(e) => setUsername(e.target.value)} />
        <div> </div>
        <label style={{ margin: '10px' }}> Password</label>
        <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} />
        <div> </div>
        <label style={{ margin: '10px' }}> Email</label>
        <input type="text" value={email} onChange={(e) => setEmail(e.target.value)} />
        <div> </div>
        <button type="submit" disabled={loading}>
          {loading ? 'Submitting...' : 'Submit'}
        </button>
      </form>

      {/* Display error message */}
      {error && (
        <div style={{ border: '1px solid red', padding: '10px', margin: '10px 0', color: 'red' }}>
          {error}
        </div>
      )}
    </div>
  );
};

export default Registration;
