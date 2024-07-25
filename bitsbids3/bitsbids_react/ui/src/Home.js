import AppHeader from './Components/Navbar/AppHeader'
import React from 'react';
import CategoryBanner from './Components/Category Banner/CategorBanner';

function Home() {
  return (
    <div className='my-3'>
      <AppHeader/>
      <CategoryBanner/>
    
    </div>
  );
}

export default Home;
