import { useEffect, useState } from 'react';
import { Routes, Route } from 'react-router-dom';
import Sidebar from '../components/Sidebar';
import Card from '../components/Card';
import Button from '../components/Button';
import Input from '../components/Input';
import Loader from '../components/Loader';
import EmptyState from '../components/EmptyState';
import ErrorState from '../components/ErrorState';
import { studentApi } from '../api/endpoints';

function StudentHome() {
  const [data, setData] = useState(null); const [loading, setLoading] = useState(true); const [error, setError] = useState('');
  useEffect(() => { studentApi.dashboard().then((r) => setData(r.data)).catch(setError).finally(() => setLoading(false)); }, []);
  if (loading) return <Loader label="Loading student dashboard" />;
  if (error) return <ErrorState message={error} />;
  return <Card title="Student dashboard" subtitle="/api/student/dashboard"><pre className="json-block">{JSON.stringify(data, null, 2)}</pre></Card>;
}

function RoomDetails() {
  const [data, setData] = useState(null); const [loading, setLoading] = useState(true); const [error, setError] = useState('');
  useEffect(() => { studentApi.roomDetails().then((r) => setData(r.data)).catch(setError).finally(() => setLoading(false)); }, []);
  if (loading) return <Loader label="Loading room details" />;
  if (error) return <ErrorState message={error} />;
  return <Card title="Room details" subtitle="/api/student/room-details"><pre className="json-block">{JSON.stringify(data, null, 2)}</pre></Card>;
}

function Preferences() {
  const [form, setForm] = useState({ roomType: '', block: '', floor: '' }); const [msg, setMsg] = useState(''); const [error, setError] = useState('');
  const submit = async (e) => { e.preventDefault(); setMsg(''); setError(''); try { await studentApi.submitPreferences(form); setMsg('Room preference submitted successfully.'); } catch (err) { setError(err); } };
  return <Card title="Room preferences" subtitle="/api/student/room-preferences"><form className="form-grid" onSubmit={submit}><Input label="Room type" value={form.roomType} onChange={(e)=>setForm({...form, roomType:e.target.value})} /><Input label="Preferred block" value={form.block} onChange={(e)=>setForm({...form, block:e.target.value})} /><Input label="Preferred floor" value={form.floor} onChange={(e)=>setForm({...form, floor:e.target.value})} /><Button type="submit">Submit preferences</Button>{msg ? <div className="inline-success">{msg}</div> : null}{error ? <div className="inline-error">{error}</div> : null}</form></Card>;
}

function Complaints() {
  const [items, setItems] = useState([]); const [loading, setLoading] = useState(true); const [error, setError] = useState('');
  const [form, setForm] = useState({ title: '', category: '', description: '', priority: '', attachment: null });
  const load = () => { setLoading(true); studentApi.complaints().then((r)=>setItems(r.data || [])).catch(setError).finally(()=>setLoading(false)); };
  useEffect(load, []);
  const submit = async (e) => { e.preventDefault(); const fd = new FormData(); fd.append('title', form.title); fd.append('category', form.category); fd.append('description', form.description); fd.append('priority', form.priority); if (form.attachment) fd.append('file', form.attachment); try { await studentApi.createComplaint(fd); load(); } catch (err) { setError(err); } };
  return <div className="stack"><Card title="Create complaint" subtitle="Supports multipart/form-data"><form className="form-grid" onSubmit={submit}><Input label="Title" value={form.title} onChange={(e)=>setForm({...form, title:e.target.value})} required /><Input label="Category" value={form.category} onChange={(e)=>setForm({...form, category:e.target.value})} required /><label className="field"><span>Description</span><textarea value={form.description} onChange={(e)=>setForm({...form, description:e.target.value})} rows="5" required /></label><Input label="Priority" value={form.priority} onChange={(e)=>setForm({...form, priority:e.target.value})} required /><label className="field"><span>Attachment</span><input type="file" onChange={(e)=>setForm({...form, attachment:e.target.files?.[0] || null})} /></label><Button type="submit">Submit complaint</Button></form></Card>{loading ? <Loader label="Loading complaint history" /> : error ? <ErrorState message={error} /> : items.length ? <div className="grid two">{items.map((item, idx)=><Card key={item.id || idx} title={item.title || item.subject || `Complaint ${idx+1}`} subtitle={item.status || 'Pending'}><pre className="json-block">{JSON.stringify(item, null, 2)}</pre></Card>)}</div> : <EmptyState title="No complaints yet" description="Your complaint history from /api/student/complaints will appear here." />}</div>;
}

function Payments() {
  const [data, setData] = useState([]); const [loading, setLoading] = useState(true); const [error, setError] = useState('');
  useEffect(() => { studentApi.payments().then((r)=>setData(r.data || [])).catch(setError).finally(()=>setLoading(false)); }, []);
  if (loading) return <Loader label="Loading payments" />; if (error) return <ErrorState message={error} />;
  if (!data.length) return <EmptyState title="No payments found" description="Student payment records from /api/student/payments will show here." />;
  return <Card title="Payments" subtitle="/api/student/payments"><pre className="json-block">{JSON.stringify(data, null, 2)}</pre></Card>;
}

export default function StudentPortal() {
  const items = [{to:'/student',label:'Dashboard',end:true},{to:'/student/room',label:'Room details'},{to:'/student/preferences',label:'Room preferences'},{to:'/student/complaints',label:'Complaints'},{to:'/student/payments',label:'Payments'}];
  return <main className="portal"><Sidebar items={items} /><section className="portal-content"><Routes><Route index element={<StudentHome />} /><Route path="room" element={<RoomDetails />} /><Route path="preferences" element={<Preferences />} /><Route path="complaints" element={<Complaints />} /><Route path="payments" element={<Payments />} /></Routes></section></main>;
}
