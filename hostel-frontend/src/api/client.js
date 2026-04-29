import axios from 'axios';
import { storage } from '../utils/storage';

const client = axios.create({
  baseURL: 'http://localhost:8080',
  timeout: 15000
});

client.interceptors.request.use((config) => {
  const token = storage.getToken();
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  return config;
});

client.interceptors.response.use(
  (response) => response,
  (err) => Promise.reject(err?.response?.data?.message || 'Something went wrong')
);

export default client;
