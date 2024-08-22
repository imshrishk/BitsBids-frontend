"use client";

import useLogin from "@/hooks/useLogin";
import React from "react";
import Link from "next/link";

/**
 * The LoginButton component.
 */
export default function LoginButton() {
  const [loggedIn, setLoggedIn] = React.useState(false);
  const { checkLoginWIthoutRedirect } = useLogin();

  /**
   * Checks the login status and updates the state.
   */
  const checkLoginStatus = async () => {
    const res = await checkLoginWIthoutRedirect();
    setLoggedIn(res);
  };

  React.useEffect(() => {
    checkLoginStatus();
  }, [checkLoginWIthoutRedirect]);

  return (
    <Link
      className="text-sm font-medium hover:underline underline-offset-4"
      href={loggedIn ? "/profile" : "/login"}
    >
      {loggedIn ? "Profile" : "Login"}
    </Link>
  );
}