import React from 'react';
import { useParams } from 'react-router-dom';
import AppHeader from './Components/Navbar/AppHeader'
import CategoryBanner from './Components/Category Banner/CategorBanner';
import MyNavbar from './Components/Navbar/MyNavbar';
import HomeBanner from './Components/Home Banner/HomeBanner';
const Home = () => {
  const { username } = useParams();

  return (     
    <div className='my-3' style={{ backgroundColor: 'darkgray' }}>
       <MyNavbar />
            <h2>Welcome  {username}!</h2>
      {/* <AppHeader/> */}
      <HomeBanner/>

      <CategoryBanner/>
    
    </div>
  );
};

export default Home;

