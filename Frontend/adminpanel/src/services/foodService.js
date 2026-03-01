import axios from "axios";

const API_URL = `${import.meta.env.VITE_API_URL}/foods`;

// Axios instance with token automatically attached
const axiosInstance = axios.create();

axiosInstance.interceptors.request.use((config) => {
  const token = localStorage.getItem("token");

  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }

  return config;
});

export const addFood = async (foodData, image) => {

  const formData = new FormData();
  formData.append("food", JSON.stringify(foodData));
  formData.append("file", image);

  try {
    await axiosInstance.post(API_URL, formData);
  } catch (error) {
    console.log("Error adding food:", error);
    throw error;
  }
};

export const getFoodList = async () => {
  try {
    const response = await axiosInstance.get(API_URL);
    return response.data;
  } catch (error) {
    console.log("Error fetching food list:", error);
    throw error;
  }
};

export const deleteFood = async (foodId) => {
  try {
    const response = await axiosInstance.delete(`${API_URL}/${foodId}`);
    return response.status === 204;
  } catch (error) {
    console.log("Error deleting food:", error);
    throw error;
  }
};
