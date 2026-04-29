import { Navigate, Route, Routes } from 'react-router-dom';
import { useMemo, useState } from 'react';
import Navbar from './components/Navbar';
import { storage } from './utils/storage';
import { normalizeRole } from './utils/auth';
import LandingPage from './pages/LandingPage';
import LoginPage from './pages/LoginPage';
import RegisterPage from './pages/RegisterPage';
import StudentPortal from './pages/StudentPortal';
import StaffPortal from './pages/StaffPortal';
import AdminPortal from './pages/AdminPortal';

function ProtectedRoute({ roles, children }) {
  const user = storage.getUser();
  const role = normalizeRole(user);
  const token = storage.getToken();
  if (!token) return <Navigate to="/login" replace />;
  if (!roles.includes(role)) return <Navigate to="/login" replace />;
  return children;
}

export default function App() {
  const [tick, setTick] = useState(0);
  const user = useMemo(() => storage.getUser(), [tick]);
  return (
    <>
      <Navbar user={user} />
      <Routes>
        <Route path="/" element={<LandingPage />} />
        <Route path="/login" element={<LoginPage onAuth={() => setTick((n) => n + 1)} />} />
        <Route path="/register" element={<RegisterPage />} />
        <Route path="/student/*" element={<ProtectedRoute roles={["STUDENT"]}><StudentPortal /></ProtectedRoute>} />
        <Route path="/staff/*" element={<ProtectedRoute roles={["STAFF"]}><StaffPortal /></ProtectedRoute>} />
        <Route path="/admin/*" element={<ProtectedRoute roles={["ADMIN"]}><AdminPortal /></ProtectedRoute>} />
      </Routes>
    </>
  );
}
