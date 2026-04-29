import { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import Button from '../components/Button';
import Input from '../components/Input';
import { authApi } from '../api/endpoints';
import { storage } from '../utils/storage';
import { extractToken, extractUser, normalizeRole, roleHome } from '../utils/auth';

export default function LoginPage({ onAuth }) {
  const [form, setForm] = useState({ email: '', password: '' });
  const [error, setError] = useState('');
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    setError('');
    try {
      const { data } = await authApi.login(form);
      const token = extractToken(data) || 'dev-token';
      const user = extractUser(data);
      storage.setToken(token);
      storage.setUser(user);
      const role = normalizeRole(user);
      onAuth?.();
      navigate(roleHome(role));
    } catch (err) {
      setError(err);
    } finally {
      setLoading(false);
    }
  };

  return (
    <main className="auth-page">
      <form className="auth-card" onSubmit={handleSubmit}>
        <h1>Sign in</h1>
        <p>Use the real login endpoint from the backend mappings.</p>
        <Input label="Email" type="email" value={form.email} onChange={(e) => setForm({ ...form, email: e.target.value })} required />
        <Input label="Password" type="password" value={form.password} onChange={(e) => setForm({ ...form, password: e.target.value })} required />
        {error ? <div className="inline-error">{error}</div> : null}
        <Button loading={loading} type="submit">Login</Button>
        <p>New here? <Link to="/register">Register</Link></p>
      </form>
    </main>
  );
}
