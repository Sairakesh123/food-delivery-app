import axios from 'axios'

const API_URL = `${import.meta.env.VITE_API_URL}/foods`;

export const addFood = async (foodData, image) => {
   
    const token = localStorage.getItem("token"); 
    
    const formData = new FormData();
    formData.append('food', JSON.stringify(foodData));
    formData.append('file', image);

    try {
        await axios.post(API_URL, formData, {
            headers: {
                "Authorization": `Bearer ${token}`
            }
        });   
    } catch (error) {
        console.log('Error', error);
        throw error;
    }
}

export const getFoodList = async () => {
    try {
        const response = await axios.get(API_URL);
        return response.data;
    } catch(error){
       console.log('Error fetching food list',error);
       throw error;
    }
}

export const deleteFood = async (foodId) => {

    const token = localStorage.getItem("token");

    try {
        const response = await axios.delete(`${API_URL}/${foodId}`, {
            headers: {
                Authorization: `Bearer ${token}`
            }
        });

        return response.status === 204;

    } catch (error) {
        console.log('Error while deleting the food.', error);
        throw error;
    }
}
