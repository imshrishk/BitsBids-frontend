import React from "react";
import Banner1 from "../../assets/images/banner_1.jpg";
import "./Banner.css";

function CategoryBanner(props) {
  return (
    <div className="banner" style={{ marginTop: "70px" }}>
      <div className="container">
        <div className="row">
          <div className="col-md-4">
            <div
              className="box"
              style={{
                backgroundImage: `url(${Banner1})`,
                backgroundPosition: "center top", // Adjust this line
              }}
              data-aos="fade-right"
            >
              <div className="banner_category">
              <a href="categories.html" style={{ 
                  color: "white", // text color
                  fontSize: "24px", // font size
                  fontWeight: "bold", // font weight
                  textShadow: "2px 2px 4px rgba(0, 0, 0, 0.7)" // optional text shadow
                }}>women's</a>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default CategoryBanner;
