import React, { useState, useEffect } from 'react';

function TaskEditModal({ task, onSave, onClose }) {
  const [title, setTitle] = useState('');
  const [description, setDescription] = useState('');
  const [status, setStatus] = useState('PENDING');
  const [errors, setErrors] = useState({});

  useEffect(() => {
    if (task) {
      setTitle(task.title);
      setDescription(task.description || '');
      setStatus(task.status);
    }
  }, [task]);

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
    onSave({ title: title.trim(), description: description.trim(), status });
  }

  if (!task) return null;

  return (
    <div className="modal-overlay" onClick={onClose}>
      <div className="modal" onClick={e => e.stopPropagation()}>
        <h2>Editar Tarefa #{task.id}</h2>
        <form onSubmit={handleSubmit}>
          <div className="form-group">
            <label>Título *</label>
            <input type="text" value={title} onChange={e => setTitle(e.target.value)} maxLength={100} />
            {errors.title && <span className="error">{errors.title}</span>}
          </div>
          <div className="form-group">
            <label>Descrição</label>
            <textarea value={description} onChange={e => setDescription(e.target.value)} maxLength={500} />
            {errors.description && <span className="error">{errors.description}</span>}
          </div>
          <div className="form-group">
            <label>Status</label>
            <select value={status} onChange={e => setStatus(e.target.value)}>
              <option value="PENDING">Pendente</option>
              <option value="IN_PROGRESS">Em Andamento</option>
              <option value="DONE">Concluída</option>
            </select>
          </div>
          <div className="modal-actions">
            <button type="submit" className="btn">Salvar</button>
            <button type="button" className="btn btn-secondary" onClick={onClose}>Cancelar</button>
          </div>
        </form>
      </div>
    </div>
  );
}

export default TaskEditModal;
