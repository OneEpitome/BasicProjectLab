import { useEffect, useState } from "react";
import { Button } from "react-bootstrap";
import { Link } from "react-router-dom";

function Review({ review, getReviewList }) {
  const onClickDeleteBtn = async () => {
    await fetch("/api/delete/review/" + review.id, { method: "delete" });
    getReviewList();
  };
  const [imagePath, setImagePath] = useState([]);

  const [userId, setUserId] = useState(null);
  useEffect(() => {
    const userIdSetting = async () => {
      setUserId(await (await fetch("/api/userId")).text());
    };
    userIdSetting();
  }, []);

  return (
    <>
      <h1>{review.title}</h1>
      <h2>{review.content}</h2>
      <h3>{review.creatorId}</h3>
      <img
        src={"http://localhost:3000/api/images/" + review.fileName}
        width="100px"
        height="50px"
      />
      {userId == review.creatorId ? (
        <Link to={"/edit/review/" + review.id}>
          <Button variant="primary">리뷰 수정</Button>
        </Link>
      ) : null}

      {userId == review.creatorId ? (
        <Button variant="primary" onClick={onClickDeleteBtn}>
          리뷰 삭제
        </Button>
      ) : null}
    </>
  );
}

export default Review;
