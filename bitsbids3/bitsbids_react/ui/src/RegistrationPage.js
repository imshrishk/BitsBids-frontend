import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import AppHeader from './Components/Navbar/AppHeader';
import Registration from './Components/Registration/Registration';
import Home from './Home';

function Registrationn() {
  return (
    
      <div className='my-3'>
        <AppHeader />
        <Registration />
        
      </div>
    
  );
}

export default Registrationn;
