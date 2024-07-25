import React from 'react';
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import AppHeader from './Components/Navbar/AppHeader';
import Registration from './Components/Registration/Registration';
import Home from './Home';
import RegistrationPage from './RegistrationPage';
import Login from './Components/Login/Login';
import { usernameExport } from './Components/Registration/Registration';
import MyNavbar from './Components/Navbar/MyNavbar';

function App() {
  const UserLink =(
    <div>
      <Link to={"/user/" + usernameExport}>{usernameExport}</Link>
    </div>
  );

  
  return (
    <Router>
      <div className='my-3'>
        {/* <AppHeader /> */}
        {/* <MyNavbar/> */}

        <Routes>
          <Route path='/user/:username' element={<Home/>}/>
          <Route path="/home" element={<Home />} />
          <Route path="/RegistrationPage" element={<RegistrationPage />} />
          <Route path="/login" element={<Login />} />

          {/* Update the Route for the Home component */}
          <Route path="/home/:username" element={<Home />} />
        </Routes>
        {/*<BestSeller />*/}
      </div>
    </Router>
  );
}

export default App;
