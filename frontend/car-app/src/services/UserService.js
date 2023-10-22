import http from "../http-common";

class UserService {


  update(data) {
    return http.put(`/cars`, data);
  }

  delete(id) {
    return http.delete(`/cars/${id}`);
  }


  findByUsername(username) {
    return http.get(`/cars?username=${username}`);
  }
}

export default new UserService();
