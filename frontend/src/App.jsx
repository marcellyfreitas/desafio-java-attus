import React, { useState, useEffect } from 'react';
import TaskList from './components/TaskList';
import TaskForm from './components/TaskForm';
import { listTasks, createTask } from './services/taskService';
import './App.css';

function App() {
  const [tasks, setTasks] = useState([]);

  useEffect(() => { loadTasks(); }, []);

  function loadTasks() {
    listTasks().then(setTasks).catch(console.error);
  }

  function handleCreate(data) {
    createTask(data).then(loadTasks).catch(console.error);
  }

  return (
    <div className="container">
      <h1>Gerenciador de Tarefas</h1>
      <TaskForm onSubmit={handleCreate} />
      <TaskList tasks={tasks} onEdit={() => {}} onDelete={() => {}} />
    </div>
  );
}

export default App;
