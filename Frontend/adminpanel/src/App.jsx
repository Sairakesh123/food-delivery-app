import { Routes, Route, Navigate } from "react-router-dom";
import AddFood from "./pages/AddFood/AddFood";
import ListFood from "./pages/ListFood/ListFood";
import Orders from "./pages/Orders/Orders";
import Login from "./pages/Login/Login";
import Sidebar from "./components/Sidebar/Sidebar";
import Menubar from "./components/Menubar/Menubar";
import { useState } from "react";

function ProtectedRoute({ children }) {
  const token = localStorage.getItem("token");
  return token ? children : <Navigate to="/login" />;
}

function Layout({ children }) {
  const [sidebarVisible, setSidebarVisible] = useState(true);

  const toggleSidebar = () => {
    setSidebarVisible(!sidebarVisible);
  };

  return (
    <div style={{ display: "flex" }}>
      <Sidebar sidebarVisible={sidebarVisible} />
      <div style={{ flex: 1 }}>
        <Menubar toggleSidebar={toggleSidebar} />
        <div style={{ padding: "20px" }}>
          {children}
        </div>
      </div>
    </div>
  );
}

function App() {
  return (
    <Routes>

      <Route path="/login" element={<Login />} />

      <Route
        path="/"
        element={
          <ProtectedRoute>
            <Layout>
              <AddFood />
            </Layout>
          </ProtectedRoute>
        }
      />

      <Route
        path="/list"
        element={
          <ProtectedRoute>
            <Layout>
              <ListFood />
            </Layout>
          </ProtectedRoute>
        }
      />

      <Route
        path="/orders"
        element={
          <ProtectedRoute>
            <Layout>
              <Orders />
            </Layout>
          </ProtectedRoute>
        }
      />

    </Routes>
  );
}

export default App;
