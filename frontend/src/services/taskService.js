import axios from 'axios';

const api = axios.create({
  baseURL: '/api'
});

export const listTasks = () => api.get('/tasks').then(r => r.data);

export const getTask = (id) => api.get(`/tasks/${id}`).then(r => r.data);

export const createTask = (data) => api.post('/tasks', data).then(r => r.data);

export const updateTask = (id, data) => api.put(`/tasks/${id}`, data).then(r => r.data);

export const deleteTask = (id) => api.delete(`/tasks/${id}`).then(r => r.data);
