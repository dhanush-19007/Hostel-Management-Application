import api from './axios'
export const getStudentComplaints = () => api.get('/api/student/complaints')
export const createComplaint = (formData) => api.post('/api/student/complaints', formData, {
  headers: { 'Content-Type': 'multipart/form-data' }
})
