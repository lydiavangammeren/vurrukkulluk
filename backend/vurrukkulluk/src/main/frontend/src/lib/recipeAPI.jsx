import axios from "axios";

// Switch baseURL for BACKEND API
export default axios.create({
  // baseURL: 'http://localhost:3004/'
  baseURL: 'http://localhost:8080/api'

});
