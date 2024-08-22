import { getAuth, signInWithPopup, GoogleAuthProvider } from "firebase/auth";
import { provider } from "@/firebase";
import axios from "axios";

/**
 * The useLogin hook.
 */
const useLogin = () => {
  const auth = getAuth();

  /**
   * Handles the login process.
   */
  const handleLogin = async () => {
    try {
      const result = await signInWithPopup(auth, provider);
      const credential = GoogleAuthProvider.credentialFromResult(result);
      const token = credential?.accessToken;
      const user = result.user;
      const email = user.email;

      if (!email) {
        return;
      }

      // Check if email is a valid BITS Pilani email
      // if (
      //   email.startsWith("f") &&
      //   email.endsWith("@hyderabad.bits-pilani.ac.in")
      // ) {
      const response = await axios.post("/api/login", {
        email: email,
        name: user.displayName,
        photoURL: user.photoURL,
        uid: user.uid,
      });

      if (response.data.login) {
        window.location.href = "/products";
        localStorage.setItem("user", JSON.stringify(response.data.login.userId));
      } else {
        window.location.href = "/login";
        alert("Login Failed");
      }
      // } else {
      //   // show error message
      // }
    } catch (error) {
      console.log(error);
    }
  };

  /**
   * Checks if the user is logged in.
   */
  const checkLogin = async () => {
    const user = localStorage.getItem("user");

    if (user) {
      try {
        const response = await axios.post("/api/checkLogin", {
          userId: user,
        });

        if (response.data.login) {
          return true;
        } else {
          window.location.href = "/login";
          return false;
        }
      } catch (error) {
        console.log(error);
        window.location.href = "/login";
        return false;
      }
    } else {
      window.location.href = "/login";
      return false;
    }
  };

  /**
   * Checks if the user is logged in without redirecting.
   */
  const checkLoginWIthoutRedirect = async () => {
    const user = localStorage.getItem("user");

    if (user) {
      try {
        const response = await axios.post("/api/checkLogin", {
          userId: user,
        });

        if (response.data.login) {
          return true;
        } else {
          return false;
        }
      } catch (error) {
        console.log(error);
        return false;
      }
    } else {
      return false;
    }
  };

  return { handleLogin, checkLogin, checkLoginWIthoutRedirect };
};

export default useLogin;