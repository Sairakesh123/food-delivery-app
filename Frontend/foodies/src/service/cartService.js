import axios from "axios";

const BASE_URL = import.meta.env.VITE_API_BASE_URL;
const API_URL = `${BASE_URL}/api/cart`;

export const addToCart = async (foodId, token) => {
    await axios.post(API_URL, { foodId }, {
        headers: { Authorization: `Bearer ${token}` }
    });
};

export const removeQtyFromCart = async (foodId, token) => {
    await axios.post(`${API_URL}/remove`, { foodId }, {
        headers: { Authorization: `Bearer ${token}` }
    });
};

export const getCartData = async (token) => {
    const response = await axios.get(API_URL, {
        headers: { Authorization: `Bearer ${token}` }
    });
    return response.data.items;
};
