import React, { useContext, useEffect, useState } from 'react';
import { StoreContext } from '../../context/StoreContext';
import axios from 'axios';
import { assets } from '../../assets/assets';
import './MyOrders.css';

const API_BASE_URL = import.meta.env.VITE_API_URL;

const MyOrders = () => {
  const { token } = useContext(StoreContext);
  const [data, setData] = useState([]);
  const [loadingRefresh, setLoadingRefresh] = useState(false);

  const fetchOrders = async () => {
    try {
      setLoadingRefresh(true);

      const response = await axios.get(
        `${API_BASE_URL}/api/orders`,
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );

      setData(response.data || []);
    } catch (err) {
      console.error('Error fetching orders', err);
    } finally {
      setLoadingRefresh(false);
    }
  };

  useEffect(() => {
    if (token) {
      fetchOrders();
    }
  }, [token]);

  return (
    <div className="container">
      <div className="py-5 row justify-content-center">
        <div className="col-11 card">
          <table className="table table-responsive mb-0">
            <tbody>
              {data.map((order, index) => (
                <tr key={order.id || index}>
                  <td style={{ width: '60px', verticalAlign: 'middle' }}>
                    <img src={assets.delivery} alt="" height={48} width={48} />
                  </td>

                  <td style={{ verticalAlign: 'middle' }}>
                    <div className="order-items">
                      {order.orderedItems
                        .map((item) => `${item.name} x ${item.quantity}`)
                        .join(', ')}
                    </div>
                    <div className="small text-muted mt-1">
                      {order.userAddress}
                    </div>
                  </td>

                  <td style={{ width: '120px', verticalAlign: 'middle' }}>
                    ₹{Number(order.amount).toFixed(2)}
                  </td>

                  <td style={{ width: '120px', verticalAlign: 'middle' }}>
                    Items: {order.orderedItems.length}
                  </td>

                  <td
                    style={{ width: '220px', verticalAlign: 'middle' }}
                    className="text-capitalize fw-bold"
                  >
                    ● {order.orderStatus}
                  </td>

                  <td
                    style={{
                      width: '72px',
                      verticalAlign: 'middle',
                      textAlign: 'center',
                    }}
                  >
                    <button
                      className={`btn refresh-btn ${
                        loadingRefresh ? 'loading' : ''
                      }`}
                      onClick={fetchOrders}
                      title="Refresh orders"
                    >
                      <i className="bi bi-arrow-clockwise" />
                    </button>
                  </td>
                </tr>
              ))}

              {data.length === 0 && (
                <tr>
                  <td colSpan={6} className="text-center py-5 text-muted">
                    No orders yet.
                  </td>
                </tr>
              )}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
};

export default MyOrders;
