import axios from "axios";

export default axios.create({
    baseURL: "http://localhost:18080",
    responseType: "json"
})
