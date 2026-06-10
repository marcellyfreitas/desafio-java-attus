import React from 'react';

const STATUS_LABEL = {
  PENDING: 'Pendente',
  IN_PROGRESS: 'Em Andamento',
  DONE: 'Concluída'
};

function TaskList({ tasks, onEdit, onDelete }) {
  return (
    <table className="table">
      <thead>
        <tr>
          <th>ID</th>
          <th>Título</th>
          <th>Status</th>
          <th>Criada em</th>
          <th>Ações</th>
        </tr>
      </thead>
      <tbody>
        {tasks.length === 0 && (
          <tr><td colSpan="5">Nenhuma tarefa encontrada.</td></tr>
        )}
        {tasks.map(task => (
          <tr key={task.id}>
            <td>{task.id}</td>
            <td>{task.title}</td>
            <td>
              <span className={`badge badge-${task.status.toLowerCase()}`}>
                {STATUS_LABEL[task.status]}
              </span>
            </td>
            <td>{new Date(task.createdAt).toLocaleString('pt-BR')}</td>
            <td>
              <button className="btn btn-sm" onClick={() => onEdit(task)}>Editar</button>
              <button className="btn btn-sm btn-danger" onClick={() => onDelete(task.id)}>Excluir</button>
            </td>
          </tr>
        ))}
      </tbody>
    </table>
  );
}

export default TaskList;
