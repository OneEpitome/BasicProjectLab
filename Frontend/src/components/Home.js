import React, { useEffect, useState } from "react";
import Button from "react-bootstrap/Button";
import Modal from "react-bootstrap/Modal";
import { Link } from "react-router-dom";

function Home() {
  const [show, setShow] = useState(false);
  const [user, setUser] = useState("");

  useEffect(() => {
    const userSetting = async () => {
      setUser(await (await fetch("/api/username")).text());
    };
    userSetting();
  }, []);

  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);
  return (
    <>
      <Button variant="primary" onClick={handleShow}>
        Category
      </Button>
      <Link to={"/login"}>
        <Button variant="primary">로그인</Button>
      </Link>
      <Link to={"/register"}>
        <Button variant="primary">회원가입</Button>
      </Link>
      <Button
        variant="primary"
        onClick={() => {
          window.location.href = "/api/logout";
        }}
      >
        로그아웃
      </Button>
      <Link to={"/create/review"}>
        <Button variant="primary">리뷰작성</Button>
      </Link>

      <Modal show={show} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>Modal heading</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Link to="/seat/1">
            {" "}
            <Button variant="info">공부하기 좋은 곳</Button>{" "}
          </Link>
          <Link to="/seat/2">
            {" "}
            <Button variant="info">밥 먹기 좋은 곳</Button>{" "}
          </Link>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleClose}>
            Close
          </Button>
          <Button variant="primary" onClick={handleClose}>
            Save Changes
          </Button>
        </Modal.Footer>
      </Modal>
      {user === "anonymousUser" ? (
        <h1>로그인하세요.</h1>
      ) : (
        <h1>로그인한 유저 : {user}</h1>
      )}
    </>
  );
}

export default Home;
