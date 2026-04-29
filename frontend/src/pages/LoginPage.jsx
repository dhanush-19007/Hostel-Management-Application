import { useState } from 'react'
import { login } from '../api/authApi'

export default function LoginPage() {
  const [form, setForm] = useState({ email: '', password: '' })
  const handleSubmit = async (e) => {
    e.preventDefault()
    const res = await login(form)
    localStorage.setItem('token', res.data.token)
    alert('Logged in')
  }
  return (
    <div className="page"><div className="card"><h2>Login</h2>
      <form onSubmit={handleSubmit}>
        <input placeholder="Email" onChange={e => setForm({ ...form, email: e.target.value })} />
        <input type="password" placeholder="Password" onChange={e => setForm({ ...form, password: e.target.value })} />
        <button type="submit">Login</button>
      </form>
    </div></div>
  )
}
