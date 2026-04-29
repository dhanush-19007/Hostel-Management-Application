import { Link } from 'react-router-dom';
import Card from '../components/Card';

export default function LandingPage() {
  return (
    <main className="page">
      <section className="hero">
        <div>
          <span className="pill">Production-ready hostel management frontend</span>
          <h1>Manage rooms, complaints, and payments through one clean portal.</h1>
          <p>Built around real backend mappings for students, staff, and admin users with secure role-based navigation and practical workflows.</p>
          <div className="hero-actions">
            <Link className="btn btn-primary" to="/login">Login</Link>
            <Link className="btn btn-secondary" to="/register">Create account</Link>
          </div>
        </div>
        <div className="hero-panel">
          <div className="metric"><strong>3</strong><span>User roles</span></div>
          <div className="metric"><strong>11</strong><span>Live REST flows</span></div>
          <div className="metric"><strong>100%</strong><span>Mapped from backend file</span></div>
        </div>
      </section>
      <section className="grid three">
        <Card title="Student tools" subtitle="Dashboard, room details, preferences, complaints, payments">
          <p>Students can view accommodation status, submit room preferences, upload complaint evidence, and track payment records.</p>
        </Card>
        <Card title="Staff tools" subtitle="Complaint queue, actions, payments, filters">
          <p>Staff users can triage complaints, resolve or dismiss cases, and review payment-related data from a single workspace.</p>
        </Card>
        <Card title="Admin tools" subtitle="Rooms management and availability overview">
          <p>Admins get room inventory visibility, availability cards, and room management views driven by the mapped admin endpoint.</p>
        </Card>
      </section>
    </main>
  );
}
