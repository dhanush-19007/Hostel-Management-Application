import { Routes, Route, Link } from 'react-router-dom'
import LoginPage from './pages/LoginPage'
import RegisterPage from './pages/RegisterPage'
import StudentDashboard from './pages/student/StudentDashboard'
import AdminDashboard from './pages/admin/AdminDashboard'
import StaffComplaintsPage from './pages/staff/StaffComplaintsPage'

export default function App() {
  return (
    <div>
      <nav className="nav">
        <Link to="/">Login</Link>
        <Link to="/register">Register</Link>
        <Link to="/student">Student</Link>
        <Link to="/admin">Admin</Link>
        <Link to="/staff">Staff</Link>
      </nav>
      <Routes>
        <Route path="/" element={<LoginPage />} />
        <Route path="/register" element={<RegisterPage />} />
        <Route path="/student" element={<StudentDashboard />} />
        <Route path="/admin" element={<AdminDashboard />} />
        <Route path="/staff" element={<StaffComplaintsPage />} />
      </Routes>
    </div>
  )
}
