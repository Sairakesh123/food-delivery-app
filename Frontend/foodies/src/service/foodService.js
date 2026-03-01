import axios from "axios";

const BASE_URL = import.meta.env.VITE_API_BASE_URL;
const API_URL = `${BASE_URL}/api/foods`;

export const fetchFoodList = async () => {
    const response = await axios.get(API_URL);
    return response.data;
};

export const fetchFoodDetails = async (id) => {
    const response = await axios.get(`${API_URL}/${id}`);
    return response.data;
};
