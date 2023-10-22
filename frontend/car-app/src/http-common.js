import axios from "axios";

export default axios.create({
  baseURL: "http://localhost:9797/api/v1",
  headers: {
    "Content-type": "application/json",
    //"Authorization":""
  }
});
