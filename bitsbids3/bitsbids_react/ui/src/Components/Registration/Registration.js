import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';



export default function Registration() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [email, setEmail] = useState('');
  const [error, setError] = useState(null);

  const navigate = useNavigate();

  const textfield = {
    margin: '150px',
  };

  const padding = {
    margin: '10px',
  };


  const handleSubmit = (e) => {
    e.preventDefault();

    const user = { username, password, email };
    fetch('http://localhost:8080/user/register', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(user),
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error(`HTTP error! Status: ${response.status}`);
        }
        console.log('NEW STUDENT ADDED');
        setError(null); // Clear any previous errors
        navigate('/Home');
      })
      .catch((error) => {
        console.error('Error adding new student:', error);

        if (error.message.includes('Username already exists')) {
          setError('Username already exists. Please choose another one.');
        } else if (error.message.includes('Email already exists')) {
          setError('Email already exists. Please use a different one.');
        } else {
          setError('Error submitting the form. Please try again.'); // Set a generic error message
        }
      });
  };

  return (
    <div className="text-center" style={textfield}>
      <form>
        <label style={padding}> Username</label>
        <input type="text" value={username} onChange={(e) => setUsername(e.target.value)} />
        <div> </div>
        <label style={padding}> Password</label>
        <input type="text" value={password} onChange={(e) => setPassword(e.target.value)} />
        <div> </div>
        <label style={padding}> Email</label>
        <input type="text" value={email} onChange={(e) => setEmail(e.target.value)} />
        <div> </div>
        <button type="button" onClick={handleSubmit}>
          {' '}
          Submit{' '}
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
}
