import { Link, useNavigate } from 'react-router-dom';
import { storage } from '../utils/storage';

export default function Navbar({ user }) {
  const navigate = useNavigate();
  const logout = () => { storage.clear(); navigate('/login'); };
  return (
    <header className="topbar">
      <Link to="/" className="brand">HostelHub</Link>
      <nav>
        <Link to="/">Home</Link>
        {!user ? <Link to="/login">Login</Link> : <button onClick={logout} className="nav-btn">Logout</button>}
      </nav>
    </header>
  );
}
