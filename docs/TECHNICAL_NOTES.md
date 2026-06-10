# Notas Técnicas

## Stack Escolhida

| Camada     | Tecnologia           | Motivo                                          |
|------------|----------------------|-------------------------------------------------|
| Backend    | Spring Boot 3 + JPA  | Produtividade, maturidade, ecossistema          |
| Banco      | MySQL 8              | Confiabilidade, amplamente adotado              |
| Migração   | Flyway               | Versionamento de schema, rollback rastreável    |
| Frontend   | React + Vite         | Velocidade de desenvolvimento, DX moderna       |
| HTTP       | Axios                | API limpa, interceptors, suporte a cancelamento |
| Container  | Docker Compose       | Ambiente replicável em 1 comando                |

## Trade-offs

### Simplicidade vs. Escalabilidade
Optou-se por monorepo com backend monolíito em vez de microsserviços. Isso reduz a complexidade operacional, mas limita o scaling horizontal independente.

### ORM vs. SQL Puro
JPA/Hibernate acelera o desenvolvimento, mas pode gerar queries subótimas em cenários de alta complexidade. Para o escopo CRUD atual é adequado.

### Flyway vs. schema.sql
Flyway foi escolhido por oferecer versionamento explícito e detecção de mudanças. `schema.sql` é mais simples mas não rastreia estados intermediários.

### CSS Puro vs. Framework UI
CSS puro mantém o bundle leve e evita dependências externas. Para um projeto maior, Bootstrap ou Tailwind trariam consistência visual mais rápida.

## Melhorias Futuras

- [ ] Autenticação JWT com Spring Security
- [ ] Paginação e ordenação na listagem de tarefas
- [ ] Filtro por status no frontend
- [ ] Testes end-to-end com Cypress ou Playwright
- [ ] Dark mode
- [ ] Variáveis de ambiente para configurações sensíveis
- [ ] Health check endpoints
