# Análise de Incidente: NullPointerException ao criar tarefa sem título

## Sintoma

Usuários reportavam `NullPointerException` com status 500 ao tentar criar tarefas com título vazio ou nulo. O erro não era tratado adequadamente, resultando em uma resposta genérica de erro interno sem orientação ao cliente.

## Causa Raiz

O campo `title` no DTO `TaskRequest` não possuía validação `@NotBlank` nem `@Size`. Quando o frontend enviava um título vazio ou nulo, o `TaskService` tentava criar uma entidade `Task` com título nulo, e o `@PrePersist` no JPA falhava ao persistir devido à constraint `NOT NULL` no banco. A exceção `DataIntegrityViolationException` era capturada pelo `GlobalExceptionHandler` como `Exception` genérica, gerando um 500 sem mensagem útil.

## Correção Aplicada

1. Adicionadas as anotações `@NotBlank` e `@Size(min = 3, max = 100)` no campo `title` do `TaskRequest`.
2. Adicionada validação `@NotNull` e `@Size` no `TaskUpdateRequest`.
3. Implementado o `GlobalExceptionHandler` com `@RestControllerAdvice` tratando `MethodArgumentNotValidException` para retornar 400 com mensagens claras.
4. Adicionado log WARN no handler de validação para rastreamento.

## Medidas Preventivas

- Adicionar validação semelhante no frontend (TaskForm e TaskEditModal) para evitar chamadas desnecessárias à API.
- Configurar testes de integração que cobrem cenários de campos inválidos (título vazio, título muito curto).
- Revisar periodicamente os logs de erro em busca de padrões de exceção não tratadas.
