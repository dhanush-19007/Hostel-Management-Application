import { useState } from 'react'
import { register } from '../api/authApi'

export default function RegisterPage() {
  const [form, setForm] = useState({ fullName: '', email: '', password: '', role: 'STUDENT' })
  const handleSubmit = async (e) => {
    e.preventDefault()
    await register(form)
    alert('Registered successfully')
  }
  return (
    <div className="page"><div className="card"><h2>Register</h2>
      <form onSubmit={handleSubmit}>
        <input placeholder="Full name" onChange={e => setForm({ ...form, fullName: e.target.value })} />
        <input placeholder="Email" onChange={e => setForm({ ...form, email: e.target.value })} />
        <input type="password" placeholder="Password" onChange={e => setForm({ ...form, password: e.target.value })} />
        <select onChange={e => setForm({ ...form, role: e.target.value })}>
          <option value="STUDENT">Student</option>
          <option value="ADMIN">Admin</option>
          <option value="STAFF">Staff</option>
        </select>
        <button type="submit">Register</button>
      </form>
    </div></div>
  )
}
