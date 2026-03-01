import axios from "axios";

const BASE_URL = import.meta.env.VITE_API_BASE_URL;
const API_URL = `${BASE_URL}/api`;

export const registerUser = async (data) => {
    return await axios.post(`${API_URL}/register`, data);
};

export const login = async (data) => {
    return await axios.post(`${API_URL}/login`, data);
};
