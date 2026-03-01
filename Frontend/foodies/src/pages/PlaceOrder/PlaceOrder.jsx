import React, { useContext, useState } from "react";
import "./PlaceOrder.css";
import { assets } from "../../assets/assets";
import { StoreContext } from "../../context/StoreContext";
import { calculateCartTotals } from "../../util/cartUtils";
import axios from "axios";
import { toast } from "react-toastify";
import { RAZORPAY_KEY } from "../../util/constants";
import { useNavigate } from "react-router-dom";

const API_BASE_URL = import.meta.env.VITE_API_URL;

const PlaceOrder = () => {
  const { foodList, quantites, setQuantites, token } =
    useContext(StoreContext);

  const navigate = useNavigate();

  const [data, setData] = useState({
    firstName: "",
    lastName: "",
    email: "",
    phoneNumber: "",
    address: "",
    state: "",
    city: "",
    zip: "",
  });

  const onChangeHandler = (event) => {
    const name = event.target.name;
    const value = event.target.value;
    setData((prev) => ({ ...prev, [name]: value }));
  };

  const cartItems = foodList.filter((food) => quantites[food.id] > 0);

  const { subtotal, shipping, tax, total } =
    calculateCartTotals(cartItems, quantites);

  // ================================
  // 🟢 PLACE ORDER
  // ================================
  const onSubmitHandler = async (event) => {
    event.preventDefault();

    const orderData = {
      userAddress: `${data.firstName},${data.lastName},${data.address},${data.city},${data.state},${data.zip}`,
      phoneNumber: data.phoneNumber,
      email: data.email,
      orderedItems: cartItems.map((item) => ({
        foodId: item.id,
        quantity: quantites[item.id],
        price: item.price * quantites[item.id],
        category: item.category,
        imageUrl: item.imageUrl,
        description: item.description,
        name: item.name,
      })),
      amount: total.toFixed(2),
      orderStatus: "Food Preparing",
    };

    try {
      const response = await axios.post(
        `${API_BASE_URL}/orders/create`,
        orderData,
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );

      if (
        (response.status === 200 || response.status === 201) &&
        response.data.razorpayOrderId
      ) {
        initiateRazorPayPayment(response.data);
      } else {
        toast.error("Unable to place order. Please try again.");
      }
    } catch (error) {
      console.error("Error placing order:", error);
      toast.error("Unable to place order.");
    }
  };

  // ================================
  // 🟢 RAZORPAY
  // ================================
  const initiateRazorPayPayment = (order) => {
    const options = {
      key: RAZORPAY_KEY,
      amount: order.amount * 100,
      currency: "INR",
      name: "Food Land",
      description: "Food Order Payment",
      order_id: order.razorpayOrderId,
      handler: async function (response) {
        await verifyPayment(response);
      },
      prefill: {
        name: `${data.firstName} ${data.lastName}`,
        email: data.email,
        contact: data.phoneNumber,
      },
      theme: { color: "#3399cc" },
      modal: {
        ondismiss: async function () {
          toast.error("Payment cancelled.");
          await deleteOrder(order.id);
        },
      },
    };

    const razorpay = new window.Razorpay(options);
    razorpay.open();
  };

  // ================================
  // 🟢 VERIFY PAYMENT
  // ================================
  const verifyPayment = async (razorpayResponse) => {
    try {
      const response = await axios.post(
        `${API_BASE_URL}/orders/verify`,
        razorpayResponse,
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );

      if (response.status === 200) {
        toast.success("Payment successful!");
        await clearCart();
        navigate("/myorders");
      } else {
        toast.error("Payment failed.");
        navigate("/");
      }
    } catch (error) {
      console.error("Verification failed:", error);
      toast.error("Payment verification failed.");
    }
  };

  // ================================
  // 🟢 DELETE ORDER IF CANCELLED
  // ================================
  const deleteOrder = async (orderId) => {
    try {
      await axios.delete(`${API_BASE_URL}/orders/${orderId}`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
    } catch (error) {
      console.error(error);
    }
  };

  // ================================
  // 🟢 CLEAR CART
  // ================================
  const clearCart = async () => {
    try {
      await axios.delete(`${API_BASE_URL}/cart`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      setQuantites({});
    } catch (error) {
      console.error(error);
    }
  };

  // ================================
  // 🟢 UI
  // ================================
  return (
    <div className="container mt-4">
      <main>
        <div className="py-5 text-center">
          <img
            className="d-block mx-auto"
            src={assets.logo}
            alt=""
            width="98"
          />
        </div>

        <div className="row g-5">
          <div className="col-md-5 col-lg-4 order-md-last">
            <h4 className="mb-3 text-primary">
              Your cart ({cartItems.length})
            </h4>

            <ul className="list-group mb-3">
              {cartItems.map((item) => (
                <li
                  key={item.id}
                  className="list-group-item d-flex justify-content-between"
                >
                  <div>
                    {item.name} x {quantites[item.id]}
                  </div>
                  <strong>
                    ₹{item.price * quantites[item.id]}
                  </strong>
                </li>
              ))}

              <li className="list-group-item d-flex justify-content-between">
                <span>Total</span>
                <strong>₹{total.toFixed(2)}</strong>
              </li>
            </ul>
          </div>

          <div className="col-md-7 col-lg-8">
            <h4 className="mb-3">Billing address</h4>

            <form onSubmit={onSubmitHandler}>
              <div className="row g-3">
                <div className="col-sm-6">
                  <input
                    type="text"
                    name="firstName"
                    className="form-control"
                    placeholder="First name"
                    required
                    onChange={onChangeHandler}
                  />
                </div>

                <div className="col-sm-6">
                  <input
                    type="text"
                    name="lastName"
                    className="form-control"
                    placeholder="Last name"
                    required
                    onChange={onChangeHandler}
                  />
                </div>

                <div className="col-12">
                  <input
                    type="email"
                    name="email"
                    className="form-control"
                    placeholder="Email"
                    required
                    onChange={onChangeHandler}
                  />
                </div>

                <div className="col-12">
                  <input
                    type="number"
                    name="phoneNumber"
                    className="form-control"
                    placeholder="Phone"
                    required
                    onChange={onChangeHandler}
                  />
                </div>

                <div className="col-12">
                  <input
                    type="text"
                    name="address"
                    className="form-control"
                    placeholder="Address"
                    required
                    onChange={onChangeHandler}
                  />
                </div>
              </div>

              <hr className="my-4" />

              <button
                className="w-100 btn btn-primary btn-lg"
                type="submit"
                disabled={cartItems.length === 0}
              >
                Continue to checkout
              </button>
            </form>
          </div>
        </div>
      </main>
    </div>
  );
};

export default PlaceOrder;
