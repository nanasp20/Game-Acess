# Game Access - Projeto Integrador SENAC

Bem-vindo ao repositório oficial do Projeto Integrador. Este projeto abrange desde a estruturação do Back-end em Java até a interface Front-end Web funcional.

---

## 📝 Etapa 6: Refatoração e Arquitetura
Nesta etapa, o foco foi a limpeza de código e a aplicação de princípios de qualidade de software.
* **Objetivo:** Preparar o código legado para expansão.
* **Ações:** Aplicação de princípios SOLID e remoção de "Code Smells".
* **Resultado:** Um código mais limpo e desacoplado.

## 💾 Etapa 7: Persistência de Dados (DAO)
Implementação da conexão robusta com o banco de dados.
* **Objetivo:** Separar a lógica de acesso a dados da lógica de negócio.
* **Ações:** Implementação do padrão DAO e configuração de conexão JDBC.
* **Tecnologias:** Java 23, Maven, MySQL.

## 🌐 Etapa 8: Front-End Web
Desenvolvimento da interface visual moderna para navegadores.
* **Objetivo:** Criar uma experiência de usuário (UX/UI) responsiva.
* **Funcionalidades:** Landing Page, Login, Cadastro e Catálogo.
* **Tecnologias:** HTML5, CSS3, JavaScript.

## 🚀 Etapa 9: Integração Back-end e Refino do Sistema
Nesta etapa final, consolidamos a aplicação integrando o Front-end ao Back-end utilizando o ecossistema Spring.

**Funcionalidades Implementadas:**
* **Integração Spring Boot MVC:** Conexão total das páginas Thymeleaf com Controllers Java.
* **Persistência com Spring Data JPA:** Gestão automatizada do banco de dados para Usuários, Jogos, Aluguéis e Cartões.
* **Sistema de Aluguel Avançado:** Lógica de expiração automática de prazos e modal de extensão de dias com cálculo de valor em tempo real.
* **Filtros Inteligentes:** Barra de pesquisa funcional e tratamento de categorias (Gêneros) para evitar duplicidade.
* **Segurança de Camada:** Implementação de PIN Parental para conteúdos restritos e senha de segurança para transações.

**Qualidade e Testes (Bugtracking):**
* Realização de testes de integração para identificação e correção de falhas de roteamento (Erros 404 e 405).
* Ajuste dinâmico de links de redirecionamento para plataformas externas (Itch.io).
* Validação de fluxos de segurança e persistência de dados em tempo real.

**Tecnologias:** Java 23, Spring Boot 3, Spring Data JPA, Thymeleaf, MySQL e JavaScript.

---
Desenvolvido por **Maria Fernanda**.