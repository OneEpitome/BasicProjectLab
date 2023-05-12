import { useEffect, useState } from "react";
import Review from "./Review";

function Seat() {
  const [reviewList, setReviewList] = useState([]);
  const getReviewList = async () => {
    const a = await fetch("/api/seat/1");
    const b = await a.json();
    setReviewList(b);
  };

  useEffect(() => {
    getReviewList();
  }, []);

  return (
    <>
      {reviewList.map((e) => {
        return <Review review={e} getReviewList={getReviewList} />;
      })}
    </>
  );
}

export default Seat;
