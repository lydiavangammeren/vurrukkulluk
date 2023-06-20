import api from "../lib/recipeAPI";
import jwt from "jwt-decode";

// export const useAuthService = () => {
export const login = async (username, password) => {
    return api
      .post("/auth/authenticate", {
        email: username,
        password: password
      })
      .then(response => {
        if (response.data.token) {
          const user = jwt(response.data.token)
          localStorage.setItem("user", JSON.stringify(user));
        }

        return response.data;
      });
  }

export const logout = () => {
    localStorage.removeItem("user");
  }

export const register = async (username, email, password) => {
    return api.post("/auth/register", {
      username,
      email,
      password
    });
  }

export const getUserByEmail = async (email) => {
    return api.get(`/user/email/${email}`)
  }

export const getCurrentUser = () => {
    return JSON.parse(localStorage.getItem('user'));;
  }
// }

// export default useAuthService
