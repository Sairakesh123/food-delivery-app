import axios from "axios";

const BASE_URL = import.meta.env.VITE_API_BASE_URL;
const API_URL = `${BASE_URL}/api`;

export const registerUser = async(data) => {
    try {
        const response = await axios.post(API_URL+"/register", data);
        return response;
    } catch (error) {
        throw error;
    }
}

export const login = async (data) => {
    try {
        const response = await axios.post(API_URL+"/login", data);
        return response;
    } catch (error) {
        throw error;
    }
}
