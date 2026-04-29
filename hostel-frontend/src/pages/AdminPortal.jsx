import { useEffect, useMemo, useState } from 'react';
import { Routes, Route } from 'react-router-dom';
import Sidebar from '../components/Sidebar';
import Card from '../components/Card';
import Loader from '../components/Loader';
import EmptyState from '../components/EmptyState';
import ErrorState from '../components/ErrorState';
import { adminApi } from '../api/endpoints';

function Rooms() {
  const [data, setData] = useState([]); const [loading, setLoading] = useState(true); const [error, setError] = useState('');
  useEffect(() => { adminApi.rooms().then((r)=>setData(r.data || [])).catch(setError).finally(()=>setLoading(false)); }, []);
  const stats = useMemo(() => {
    const total = data.length;
    const available = data.filter((r)=> String(r.available ?? r.isAvailable ?? r.status).toLowerCase().includes('true') || String(r.status).toLowerCase().includes('available')).length;
    return { total, available, occupied: Math.max(total - available, 0) };
  }, [data]);
  if (loading) return <Loader label="Loading rooms" />; if (error) return <ErrorState message={error} />;
  return <div className="stack"><div className="grid three"><Card title="Total rooms"><div className="stat-value">{stats.total}</div></Card><Card title="Available"><div className="stat-value">{stats.available}</div></Card><Card title="Occupied"><div className="stat-value">{stats.occupied}</div></Card></div>{data.length ? <div className="grid two">{data.map((room, idx)=><Card key={room.id || idx} title={room.roomNumber || room.name || `Room ${idx+1}`} subtitle={room.status || (room.available ? 'Available' : 'Occupied')}><pre className="json-block">{JSON.stringify(room, null, 2)}</pre></Card>)}</div> : <EmptyState title="No rooms returned" description="Admin room records from /api/admin/rooms will appear here." />}</div>;
}

export default function AdminPortal() {
  const items = [{to:'/admin',label:'Rooms management',end:true}];
  return <main className="portal"><Sidebar items={items} /><section className="portal-content"><Routes><Route index element={<Rooms />} /></Routes></section></main>;
}
