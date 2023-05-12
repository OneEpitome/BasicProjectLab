import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";
import axios from "axios";
import { useState } from "react";
import { redirect, useNavigate } from "react-router-dom";
import { Alert } from "react-bootstrap";

function Register() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [errors, setErrors] = useState("");
  const navigate = useNavigate();

  const handleSubmit = (e) => {
    e.preventDefault();
    axios
      .post("/api/register", { username: username, password: password })
      .then((response) => {
        alert(response.data);
        navigate("/");
      })
      .catch((error) => {
        setErrors(error.response.data);
      });
  };

  return (
    <Form onSubmit={handleSubmit}>
      <Form.Group className="mb-3" controlId="formBasicEmail">
        <Form.Label>Email address</Form.Label>
        <Form.Control
          type="text"
          placeholder="유저이름"
          name="username"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
        />
        <Form.Text className="text-muted">Enter Your Account ID</Form.Text>
      </Form.Group>

      <Form.Group className="mb-3" controlId="formBasicPassword">
        <Form.Label>Password</Form.Label>
        <Form.Control
          type="password"
          placeholder="Password"
          name="password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />
      </Form.Group>
      <Form.Group className="mb-3" controlId="formBasicCheckbox">
        <Form.Check type="checkbox" label="Check me out" />
      </Form.Group>
      <Button variant="primary" type="submit">
        Submit
      </Button>
      {errors && (
        <ul>
          {errors.map((error, index) => (
            // <li key={index}>
            //   {error.field} : {error.defaultMessage}
            // </li>
            <Alert key={index} variant={"danger"}>
              {error.field} : {error.defaultMessage}
            </Alert>
          ))}
        </ul>
      )}
    </Form>
  );
}

export default Register;
