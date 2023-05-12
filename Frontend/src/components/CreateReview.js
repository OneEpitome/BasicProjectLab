import { Button } from "react-bootstrap";
import Form from "react-bootstrap/Form";

function CreateReview() {
  return (
    <Form
      method="post"
      action="/api/create/review"
      encType="multipart/form-data"
    >
      <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
        <Form.Label>Email address</Form.Label>
        <Form.Control type="text" placeholder="Title" name="title" />
      </Form.Group>
      <Form.Group className="mb-3" controlId="exampleForm.ControlTextarea1">
        <Form.Label>Example textarea</Form.Label>
        <Form.Control as="textarea" rows={3} name="content" />
      </Form.Group>
      <Form.Group controlId="formFileMultiple" className="mb-3">
        <Form.Label>Multiple files input example</Form.Label>
        <Form.Control type="file" name="image" multiple />
      </Form.Group>

      <Form.Select aria-label="Default select example" name="seat">
        <option value="1">1</option>
        <option value="2">2</option>
      </Form.Select>

      <Button type="submit" variant="primary">
        Submit
      </Button>
    </Form>
  );
}

export default CreateReview;
