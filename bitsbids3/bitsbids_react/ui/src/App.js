import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import AppHeader from './Components/Navbar/AppHeader';
import Registration from './Components/Registration/Registration';
import Home from './Home';
import RegistrationPage from './RegistrationPage';
import NavBar from './Components/Navbar/Navbar';
import Login from './Components/Login/Login'

function App() {
  return (
    <Router>
      <div className='my-3'>
        <AppHeader />
        <Routes>
          <Route path="/home" element={<Home />} />
          <Route path="/RegistrationPage" element={<RegistrationPage />} />
          <Route path="/login" element={<Login />} />

        </Routes>
       {/*<BestSeller />*/}
      </div>
    </Router>
  );
}

export default App;
