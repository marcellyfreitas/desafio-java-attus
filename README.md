# Gerenciador de Tarefas

Aplicação full stack para gerenciamento de tarefas com CRUD completo.

## Tecnologias

- **Backend:** Java 21, Spring Boot 3.4, Spring Data JPA, Flyway, MySQL 8
- **Frontend:** React 18, Vite 5, React Router, Axios
- **Infra:** Docker, Docker Compose

## Execução

```bash
docker compose up --build
```

Acessar:
- Frontend: http://localhost:5173
- API:      http://localhost:8080/api/tasks

## Endpoints da API

### `POST /api/tasks` — Criar tarefa

```json
// Request
{ "title": "Estudar Java", "description": "Ler capítulo 5" }

// Response (201)
{ "id": 1, "title": "Estudar Java", "description": "Ler capítulo 5", "status": "PENDING", "createdAt": "2026-06-10T10:00:00" }
```

### `GET /api/tasks` — Listar tarefas

```json
// Response (200)
[{ "id": 1, "title": "Estudar Java", "description": "Ler capítulo 5", "status": "PENDING", "createdAt": "2026-06-10T10:00:00" }]
```

### `GET /api/tasks/{id}` — Buscar tarefa

```json
// Response (200)
{ "id": 1, "title": "Estudar Java", "description": "Ler capítulo 5", "status": "PENDING", "createdAt": "2026-06-10T10:00:00" }
```

### `PUT /api/tasks/{id}` — Atualizar tarefa

```json
// Request
{ "title": "Estudar Spring", "description": "Ler documentação", "status": "IN_PROGRESS" }

// Response (200)
{ "id": 1, "title": "Estudar Spring", "description": "Ler documentação", "status": "IN_PROGRESS", "createdAt": "2026-06-10T10:00:00" }
```

### `DELETE /api/tasks/{id}` — Remover tarefa

`Response: 204 No Content`

### Erros (400 / 404 / 500)

```json
{ "timestamp": "2026-06-10T10:00:00", "status": 400, "message": "title: Title must be between 3 and 100 characters" }
```

## Testes

```bash
cd backend && mvn test
```
