import { useState } from "react";
import LibraryButton from "./LibraryButton";

function LibraryLogin() {
  const [doYouHaveAnAccount, setDoYouHaveAnAccount] = useState(true);
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const handleCreateAccountClick = () => {
    setDoYouHaveAnAccount(!doYouHaveAnAccount);
  };
  const handleLogin = () => {
    alert("Username is: " + username + " and Password is: " + password);
  };

  return (
    <>
      {doYouHaveAnAccount ? (
        <div style={{ border: "3px solid blue" }}>
          <label>UserName:</label>
          <input
            type="text"
            onChange={(e) => setUsername(e.target.value)}
            placeholder="Enter Your username"
          />
          <br />
          <label>Password:</label>
          <input
            type="password"
            onChange={(e) => setPassword(e.target.value)}
          />
          <br />
          <LibraryButton buttonLabel="LOGIN" onClickButton={handleLogin} />{" "}
          <br />
          <label>New here? </label>{" "}
          <LibraryButton
            buttonLabel="Create Account"
            onClickButton={handleCreateAccountClick}
          />
        </div>
      ) : (
        <div style={{ border: "3px solid indigo" }}>
          <label>Student ID:</label>
          <input type="text" placeholder="Enter Your Student ID" />
          <br />
          <label>Email:</label>
          <input type="email" />
          <br />
          <label>Password:</label>
          <input type="password" />
          <br />
          <label>Confirm Password:</label>
          <input type="password" />
          <br />
          <LibraryButton buttonLabel="LOGIN" onClickButton={handleLogin} />{" "}
          <br />
          <label>Already have an account?? </label>{" "}
          <LibraryButton
            buttonLabel="Sign In"
            onClickButton={handleCreateAccountClick}
          />
        </div>
      )}
    </>
  );
}

export default LibraryLogin;
