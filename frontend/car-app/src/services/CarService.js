import http from "../http-common";

class CarDataService {
  getAll() {
    return http.get("/cars");
  }

  get(id) {
    return http.get(`/cars/${id}`);
  }

  create(data) {
    return http.post("/cars", data);
  }

  update(data) {
    return http.put(`/cars`, data);
  }

  delete(id) {
    return http.delete(`/cars/${id}`);
  }

  deleteAll() {
    return http.delete(`/cars`);
  }

  findByTitle(title) {
    return http.get(`/cars?title=${title}`);
  }
}

export default new CarDataService();