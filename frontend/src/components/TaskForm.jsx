import React, { useState } from 'react';

function TaskForm({ onSubmit }) {
  const [title, setTitle] = useState('');
  const [description, setDescription] = useState('');
  const [errors, setErrors] = useState({});

  function validate() {
    const errs = {};
    if (!title || title.trim().length < 3 || title.length > 100) {
      errs.title = 'Título deve ter entre 3 e 100 caracteres.';
    }
    if (description && description.length > 500) {
      errs.description = 'Descrição deve ter no máximo 500 caracteres.';
    }
    return errs;
  }

  function handleSubmit(e) {
    e.preventDefault();
    const errs = validate();
    setErrors(errs);
    if (Object.keys(errs).length > 0) return;
    onSubmit({ title: title.trim(), description: description.trim() });
    setTitle('');
    setDescription('');
  }

  return (
    <form onSubmit={handleSubmit} className="task-form">
      <h2>Nova Tarefa</h2>
      <div className="form-group">
        <label>Título *</label>
        <input
          type="text"
          value={title}
          onChange={e => setTitle(e.target.value)}
          maxLength={100}
        />
        {errors.title && <span className="error">{errors.title}</span>}
      </div>
      <div className="form-group">
        <label>Descrição</label>
        <textarea
          value={description}
          onChange={e => setDescription(e.target.value)}
          maxLength={500}
        />
        {errors.description && <span className="error">{errors.description}</span>}
      </div>
      <button type="submit" className="btn">Cadastrar</button>
    </form>
  );
}

export default TaskForm;
