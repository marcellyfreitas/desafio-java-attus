# Endpoints da API

Base URL: `http://localhost:8080/api/tasks`

---

## `POST /api/tasks` — Criar tarefa

**Request:**
```json
{
  "title": "Estudar Java",
  "description": "Ler capítulo 5"
}
```

**Response `201 Created`:**
```json
{
  "id": 1,
  "title": "Estudar Java",
  "description": "Ler capítulo 5",
  "status": "PENDING",
  "createdAt": "2026-06-10T10:00:00"
}
```

---

## `GET /api/tasks` — Listar tarefas

**Response `200 OK`:**
```json
[
  {
    "id": 1,
    "title": "Estudar Java",
    "description": "Ler capítulo 5",
    "status": "PENDING",
    "createdAt": "2026-06-10T10:00:00"
  }
]
```

---

## `GET /api/tasks/{id}` — Buscar tarefa

**Response `200 OK`:**
```json
{
  "id": 1,
  "title": "Estudar Java",
  "description": "Ler capítulo 5",
  "status": "PENDING",
  "createdAt": "2026-06-10T10:00:00"
}
```

**Response `404 Not Found`:**
```json
{
  "timestamp": "2026-06-10T10:00:00",
  "status": 404,
  "message": "Task not found with id: 99"
}
```

---

## `PUT /api/tasks/{id}` — Atualizar tarefa

**Request:**
```json
{
  "title": "Estudar Spring",
  "description": "Ler documentação",
  "status": "IN_PROGRESS"
}
```

**Response `200 OK`:**
```json
{
  "id": 1,
  "title": "Estudar Spring",
  "description": "Ler documentação",
  "status": "IN_PROGRESS",
  "createdAt": "2026-06-10T10:00:00"
}
```

---

## `DELETE /api/tasks/{id}` — Remover tarefa

**Response:** `204 No Content`

---

## Erros

Todos os erros seguem o formato:

```json
{
  "timestamp": "2026-06-10T10:00:00",
  "status": 400,
  "message": "title: Title must be between 3 and 100 characters"
}
```

| Código | Descrição                  |
|--------|----------------------------|
| 400    | Erro de validação          |
| 404    | Tarefa não encontrada      |
| 500    | Erro interno do servidor   |
