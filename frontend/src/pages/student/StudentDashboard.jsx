import { useEffect, useState } from 'react'
import { getStudentComplaints } from '../../api/complaintApi'

export default function StudentDashboard() {
  const [complaints, setComplaints] = useState([])
  useEffect(() => {
    getStudentComplaints().then(res => setComplaints(res.data)).catch(() => setComplaints([]))
  }, [])
  return (
    <div className="page">
      <div className="card"><h2>Student Dashboard</h2><p>Room booking, complaints, roommate preferences, and payments.</p></div>
      <div className="card"><h3>My Complaints</h3>{complaints.map(c => <p key={c.id}>{c.title} - {c.status}</p>)}</div>
    </div>
  )
}
