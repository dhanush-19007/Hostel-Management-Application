import client from './client';

export const authApi = {
  login: (payload) => client.post('/api/auth/login', payload),
  register: (payload) => client.post('/api/auth/register', payload)
};

export const studentApi = {
  dashboard: () => client.get('/api/student/dashboard'),
  roomDetails: () => client.get('/api/student/room-details'),
  submitPreferences: (payload) => client.post('/api/student/room-preferences', payload),
  complaints: () => client.get('/api/student/complaints'),
  createComplaint: (formData) => client.post('/api/student/complaints', formData, {
    headers: { 'Content-Type': 'multipart/form-data' }
  }),
  payments: () => client.get('/api/student/payments')
};

export const staffApi = {
  dashboard: () => client.get('/api/staff/dashboard'),
  complaints: () => client.get('/api/staff/complaints'),
  resolveComplaint: (id) => client.put(`/api/staff/complaints/${id}/resolve`),
  dismissComplaint: (id) => client.put(`/api/staff/complaints/${id}/dismiss`),
  payments: () => client.get('/api/staff/payments')
};

export const adminApi = {
  rooms: () => client.get('/api/admin/rooms')
};
