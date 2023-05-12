import { useEffect, useState } from "react";
import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";
import { useParams } from "react-router-dom";

function EditReview() {
  const { reviewId } = useParams();
  const [review, setReview] = useState([]);
  const [newTitle, setNewTitle] = useState("");
  const [newContent, setNewContent] = useState("");

  const getReview = async () => {
    setReview(await (await fetch("/api/get/review/" + reviewId)).json());
  };

  useEffect(() => {
    getReview();
  }, []);

  useEffect(() => {
    setNewTitle(review.title);
    setNewContent(review.content);
  }, [review]);

  const onChangeTitle = (event) => {
    const {
      target: { value },
    } = event;

    setNewTitle(value);
  };

  const onChangeContent = (event) => {
    const {
      target: { value },
    } = event;

    setNewContent(value);
  };

  return (
    <Form action={"/api/edit/review/" + reviewId} method="post">
      <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
        <Form.Label>Email address</Form.Label>
        <Form.Control
          type="text"
          placeholder="Title"
          name="title"
          value={newTitle}
          onChange={onChangeTitle}
        />
      </Form.Group>
      <Form.Group className="mb-3" controlId="exampleForm.ControlTextarea1">
        <Form.Label>Example textarea</Form.Label>
        <Form.Control
          as="textarea"
          rows={3}
          name="content"
          value={newContent}
          onChange={onChangeContent}
        />
      </Form.Group>

      <Form.Select aria-label="Default select example" name="seat" disabled>
        <option value="1">1</option>
        <option value="2">2</option>
      </Form.Select>

      <Button type="submit" variant="primary">
        Submit
      </Button>
    </Form>
  );
}

export default EditReview;
