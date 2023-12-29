import React from "react";
import { Carousel } from "react-bootstrap";
import BackgroundImage1 from "../../assets/images/shirts2.jpg";
import BackgroundImage2 from "../../assets/images/books.png";
import BackgroundImage3 from "../../assets/images/decor.webp";
import "./HomeBanner.css"; // Create a CSS file for custom styling
import { colors } from "@material-ui/core";

function HomeBanner(props) {
  return (
    <Carousel>
      <Carousel.Item>
        <div
          className="d-block w-100 main_slider"
          style={{
            backgroundImage: `url(${BackgroundImage1})`,
            backgroundRepeat: "no-repeat",
            backgroundSize: "cover", // This will make the image cover the entire container
            backgroundPosition: "center",
          }}
        >
          <div className="container fill_height">
            <div className="row align-items-center fill_height">
              <div className="col">
                <div className="main_slider_content" data-aos="fade-right">
                  <h6 className="banner-subtitle">NEW</h6>
                  <h1 className="banner-title">Suits For Placements</h1>
                  <div className="red_button shop_now_button">
                    <a href="#" className="banner-link">
                      Shop Now
                    </a>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </Carousel.Item>
      <Carousel.Item>
        <div
          className="d-block w-100 main_slider"
          style={{
            backgroundImage: `url(${BackgroundImage2})`,
            backgroundRepeat: "no-repeat",
            backgroundSize: "cover", // This will make the image cover the entire container
            backgroundPosition: "center",
          }}
        >
          <div className="container fill_height">
            <div className="row align-items-center fill_height">
              <div className="col">
                <div className="main_slider_content" data-aos="fade-right">
                  <h6 className="banner-subtitle">Seniors are leaving</h6>
                  <h1 className="banner-title">BUY TEXTBOOKS FOR CHEAP</h1>
                  <div className="red_button shop_now_button">
                    <a href="#" className="banner-link">
                      Explore Now
                    </a>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </Carousel.Item>
      <Carousel.Item>
        <div
          className="d-block w-100 main_slider"
          style={{
            backgroundImage: `url(${BackgroundImage3})`,
            backgroundRepeat: "no-repeat",
            backgroundSize: "cover", // This will make the image cover the entire container
            backgroundPosition: "center",
          }}
        >
          <div className="container fill_height">
            <div className="row align-items-center fill_height">
              <div className="col">
                <div className="main_slider_content" data-aos="fade-right">
                <h6 className="banner-subtitle" style={{ color: "black" }}>Give your room a makeover</h6>
                  <h1 className="banner-title"style={{ color: "black" }}>Cozy Styles for Cold Days</h1>
                  <div className="red_button shop_now_button">
                    <a href="#" className="banner-link">
                      Shop Winter
                    </a>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </Carousel.Item>
    </Carousel>
  );
}

export default HomeBanner;
