import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import AppHeader from './Components/Navbar/AppHeader';
import Registration from './Components/Registration/Registration';
import Home from './Home';
import Login from './Components/Login'

function LoginPage() {
  return (
    
      <div className='my-3'>
        <AppHeader />
        <Login />
        
      </div>
    
  );
}

export default LoginPage;
