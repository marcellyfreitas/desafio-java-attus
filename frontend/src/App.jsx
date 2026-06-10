import React, { useState, useEffect } from 'react';
import TaskList from './components/TaskList';
import TaskForm from './components/TaskForm';
import TaskEditModal from './components/TaskEditModal';
import { listTasks, createTask, updateTask, deleteTask } from './services/taskService';
import './App.css';

function App() {
  const [tasks, setTasks] = useState([]);
  const [editingTask, setEditingTask] = useState(null);

  useEffect(() => { loadTasks(); }, []);

  function loadTasks() {
    listTasks().then(setTasks).catch(console.error);
  }

  function handleCreate(data) {
    createTask(data).then(loadTasks).catch(console.error);
  }

  function handleEdit(task) {
    setEditingTask(task);
  }

  function handleSave(data) {
    updateTask(editingTask.id, data)
      .then(() => { setEditingTask(null); loadTasks(); })
      .catch(console.error);
  }

  function handleDelete(id) {
    if (!window.confirm('Excluir esta tarefa?')) return;
    deleteTask(id).then(loadTasks).catch(console.error);
  }

  return (
    <div className="container">
      <h1>Gerenciador de Tarefas</h1>
      <TaskForm onSubmit={handleCreate} />
      <TaskList tasks={tasks} onEdit={handleEdit} onDelete={handleDelete} />
      {editingTask && (
        <TaskEditModal task={editingTask} onSave={handleSave} onClose={() => setEditingTask(null)} />
      )}
    </div>
  );
}

export default App;
