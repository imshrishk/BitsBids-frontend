import React from "react";
import box_glove from "../../assets/images/box_glove.jpg";
import books from "../../assets/images/books.png";
import decor from "../../assets/images/decor.webp";
import "./Banner.css";

function CategoryBanner(props) {
  return (
<box_flex>
            <div
              className="box"
              style={{
                backgroundImage: `url(${box_glove})`,
                backgroundPosition: "center top", // Adjust this line
              }}
              data-aos="fade-right"
            >
              
              <a href="categories.html" style={{ 
                  color: "white", // text color
                  fontSize: "24px", // font size
                  fontWeight: "bold", // font weight
                  textShadow: "2px 2px 4px rgba(0, 0, 0, 0.7)" // optional text shadow
                }}>Sports Items</a>
              
            </div>
            <div
              className="box"
              style={{
                backgroundImage: `url(${books})`,
                backgroundPosition: "center top", // Adjust this line
              }}
              data-aos="fade-right"
            >
              
              <a href="categories.html" style={{ 
                  color: "white", // text color
                  fontSize: "24px", // font size
                  fontWeight: "bold", // font weight
                  textShadow: "2px 2px 4px rgba(0, 0, 0, 0.7)" // optional text shadow
                }}>Books</a>
              
            </div>
            <div
              className="box"
              style={{
                backgroundImage: `url(${decor})`,
                backgroundPosition: "center top", // Adjust this line
              }}
              data-aos="fade-right"
            >
              
              <a href="categories.html" style={{ 
                  color: "white", // text color
                  fontSize: "24px", // font size
                  fontWeight: "bold", // font weight
                  textShadow: "2px 2px 4px rgba(0, 0, 0, 0.7)" // optional text shadow
                }}>Room Decor</a>
              
            </div>
            </box_flex>
          
  );
}

export default CategoryBanner;
