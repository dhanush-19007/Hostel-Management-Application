import { useEffect, useMemo, useState } from 'react';
import { Routes, Route } from 'react-router-dom';
import Sidebar from '../components/Sidebar';
import Card from '../components/Card';
import Button from '../components/Button';
import Loader from '../components/Loader';
import EmptyState from '../components/EmptyState';
import ErrorState from '../components/ErrorState';
import { staffApi } from '../api/endpoints';

function Dashboard() {
  const [data, setData] = useState(null); const [loading, setLoading] = useState(true); const [error, setError] = useState('');
  useEffect(() => { staffApi.dashboard().then((r)=>setData(r.data)).catch(setError).finally(()=>setLoading(false)); }, []);
  if (loading) return <Loader label="Loading staff dashboard" />; if (error) return <ErrorState message={error} />;
  return <Card title="Staff dashboard" subtitle="/api/staff/dashboard"><pre className="json-block">{JSON.stringify(data, null, 2)}</pre></Card>;
}

function Complaints() {
  const [items, setItems] = useState([]); const [query, setQuery] = useState(''); const [loading, setLoading] = useState(true); const [error, setError] = useState('');
  const load = () => { setLoading(true); staffApi.complaints().then((r)=>setItems(r.data || [])).catch(setError).finally(()=>setLoading(false)); };
  useEffect(load, []);
  const filtered = useMemo(() => items.filter((item) => JSON.stringify(item).toLowerCase().includes(query.toLowerCase())), [items, query]);
  const act = async (type, id) => { try { await (type === 'resolve' ? staffApi.resolveComplaint(id) : staffApi.dismissComplaint(id)); load(); } catch (err) { setError(err); } };
  if (loading) return <Loader label="Loading complaint queue" />; if (error) return <ErrorState message={error} />;
  return <div className="stack"><Card title="Complaint queue" subtitle="Filter and action workspace"><input className="search" placeholder="Search complaints" value={query} onChange={(e)=>setQuery(e.target.value)} /></Card>{filtered.length ? <div className="grid two">{filtered.map((item, idx)=><Card key={item.id || idx} title={item.title || item.subject || `Complaint ${idx+1}`} subtitle={item.status || 'Open'} action={<div className="row gap"><Button variant="secondary" onClick={()=>act('resolve', item.id)}>Resolve</Button><Button variant="ghost" onClick={()=>act('dismiss', item.id)}>Dismiss</Button></div>}><pre className="json-block">{JSON.stringify(item, null, 2)}</pre></Card>)}</div> : <EmptyState title="No complaints found" description="Staff complaint records from /api/staff/complaints are empty or filtered out." />}</div>;
}

function Payments() {
  const [data, setData] = useState([]); const [loading, setLoading] = useState(true); const [error, setError] = useState('');
  useEffect(() => { staffApi.payments().then((r)=>setData(r.data || [])).catch(setError).finally(()=>setLoading(false)); }, []);
  if (loading) return <Loader label="Loading staff payments" />; if (error) return <ErrorState message={error} />;
  if (!data.length) return <EmptyState title="No payment records" description="Staff payment data from /api/staff/payments will appear here." />;
  return <Card title="Payments" subtitle="/api/staff/payments"><pre className="json-block">{JSON.stringify(data, null, 2)}</pre></Card>;
}

export default function StaffPortal() {
  const items = [{to:'/staff',label:'Dashboard',end:true},{to:'/staff/complaints',label:'Complaint queue'},{to:'/staff/payments',label:'Payments'}];
  return <main className="portal"><Sidebar items={items} /><section className="portal-content"><Routes><Route index element={<Dashboard />} /><Route path="complaints" element={<Complaints />} /><Route path="payments" element={<Payments />} /></Routes></section></main>;
}
