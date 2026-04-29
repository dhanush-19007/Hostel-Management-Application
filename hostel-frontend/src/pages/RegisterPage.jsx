import { useState } from 'react';
import Button from '../components/Button';
import Input from '../components/Input';
import { authApi } from '../api/endpoints';

export default function RegisterPage() {
  const [form, setForm] = useState({ name: '', email: '', password: '', role: 'STUDENT' });
  const [message, setMessage] = useState('');
  const [error, setError] = useState('');
  const [loading, setLoading] = useState(false);

  const submit = async (e) => {
  e.preventDefault();
  setLoading(true);
  setError('');
  setMessage('');

  try {
    await authApi.register({
      fullName: form.name,
      email: form.email,
      password: form.password,
      role: form.role,
    });
    setMessage('Registration successful. You can sign in now.');
  } catch (err) {
    setError(err?.response?.data?.message || 'Something went wrong');
  } finally {
    setLoading(false);
  }
};

  return (
    <main className="auth-page">
      <form className="auth-card" onSubmit={submit}>
        <h1>Create account</h1>
        <Input label="Name" value={form.name} onChange={(e) => setForm({ ...form, name: e.target.value })} required />
        <Input label="Email" type="email" value={form.email} onChange={(e) => setForm({ ...form, email: e.target.value })} required />
        <Input label="Password" type="password" value={form.password} onChange={(e) => setForm({ ...form, password: e.target.value })} required />
        <label className="field"><span>Role</span><select value={form.role} onChange={(e) => setForm({ ...form, role: e.target.value })}><option>STUDENT</option><option>STAFF</option><option>ADMIN</option></select></label>
        {message ? <div className="inline-success">{message}</div> : null}
        {error ? <div className="inline-error">{error}</div> : null}
        <Button loading={loading} type="submit">Register</Button>
      </form>
    </main>
  );
}
