import React, { useState, useEffect } from 'react';
import TaskList from './components/TaskList';
import TaskForm from './components/TaskForm';
import TaskEditModal from './components/TaskEditModal';
import { listTasks, createTask, updateTask, deleteTask } from './services/taskService';
import './App.css';

function App() {
  const [tasks, setTasks] = useState([]);
  const [editingTask, setEditingTask] = useState(null);
  const [apiError, setApiError] = useState('');

  useEffect(() => { loadTasks(); }, []);

  function loadTasks() {
    listTasks().then(setTasks).catch(() => setApiError('Erro ao carregar tarefas.'));
  }

  function handleCreate(data) {
    createTask(data).then(() => { setApiError(''); loadTasks(); }).catch(handleApiError);
  }

  function handleEdit(task) {
    setEditingTask(task);
  }

  function handleSave(data) {
    updateTask(editingTask.id, data)
      .then(() => { setEditingTask(null); setApiError(''); loadTasks(); })
      .catch(handleApiError);
  }

  function handleDelete(id) {
    if (!window.confirm('Excluir esta tarefa?')) return;
    deleteTask(id).then(() => { setApiError(''); loadTasks(); }).catch(handleApiError);
  }

  function handleApiError(error) {
    const msg = error.response?.data?.message || 'Erro inesperado. Tente novamente.';
    setApiError(msg);
  }

  return (
    <div className="container">
      <h1>Gerenciador de Tarefas</h1>
      {apiError && <div className="api-error">{apiError}</div>}
      <TaskForm onSubmit={handleCreate} />
      <TaskList tasks={tasks} onEdit={handleEdit} onDelete={handleDelete} />
      {editingTask && (
        <TaskEditModal task={editingTask} onSave={handleSave} onClose={() => setEditingTask(null)} />
      )}
    </div>
  );
}

export default App;
