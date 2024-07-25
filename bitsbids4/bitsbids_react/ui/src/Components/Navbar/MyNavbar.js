import React from 'react';
import { Navbar, Nav, Button } from 'react-bootstrap';
import { FaUser, FaPlus } from 'react-icons/fa';
import './navbar.css';

const MyNavbar = () => {
  return (
    <Navbar bg="light" expand="lg" fixed="top" className="mb-0">
      <Navbar.Brand className="bits-text" href="#home">
        <span className="bits-text">BITS</span>
        <span className="bids-text">bids</span>
      </Navbar.Brand>
      <Navbar.Toggle aria-controls="basic-navbar-nav" />
      <Navbar.Collapse id="basic-navbar-nav">
        <Nav className="mr-auto"></Nav>
        <div className="search-bar-container">
          <input
            className="form-control search-bar"
            type="search"
            placeholder="Search..."
            aria-label="Search"
          />
        </div>
        <div className="button-container">
          <Button variant="light" className="profile-button">
            <FaUser className="icon" />
            Profile
          </Button>
          <Button variant="primary" className="sell-button">
            <FaPlus className="icon" />
            Sell
          </Button>
        </div>
      </Navbar.Collapse>
    </Navbar>
  );
};

export default MyNavbar;
