import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { assets } from '../../assets/assets';

const API_URL = `${import.meta.env.VITE_API_URL}/api/orders`;

const Orders = () => {
  const [data, setData] = useState([]);

 const fetchOrders = async () => {
  try {
    const token = localStorage.getItem("token");

    const response = await axios.get(`${API_URL}/all`, {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });

    setData(response.data);
  } catch (error) {
    console.log("Error fetching orders:", error.response);
  }
};

const updateStatus = async (event, orderId) => {
  try {
    const token = localStorage.getItem("token");

    const response = await axios.patch(
      `${API_URL}/status/${orderId}?status=${event.target.value}`,
      {},
      {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      }
    );

    if (response.status === 200) {
      await fetchOrders();
    }
  } catch (error) {
    console.log("Error updating status:", error.response);
  }
};

  useEffect(() => {
    fetchOrders();
  }, []);

  return (
    <div className='container py-5'>
      <div className='row justify-content-center'>
        <div className='col-11 card p-3'>
          <table className='table'>
            <tbody>
              {data.map((order, index) => (
                <tr key={index}>
                  <td>
                    <img src={assets.parcel} alt="" height={48} width={48} />
                  </td>
                  <td>
                    <div>
                      {order.orderedItems.map((item, i) =>
                        i === order.orderedItems.length - 1
                          ? `${item.name} x ${item.quantity}`
                          : `${item.name} x ${item.quantity}, `
                      )}
                    </div>
                    <div>{order.userAddress}</div>
                  </td>
                  <td>₹{Number(order.amount).toFixed(2)}</td>
                  <td>Items: {order.orderedItems.length}</td>
                  <td>
                    <select
                      className="form-control"
                      onChange={(event) => updateStatus(event, order.id)}
                      value={order.orderStatus}
                    >
                      <option value="Food Preparing">Food Preparing</option>
                      <option value="Out for delivery">Out for delivery</option>
                      <option value="Delivered">Delivered</option>
                    </select>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
};

export default Orders;
