# 🧠 Smart Agent

---

Smart Agent is a [**Spring AI**](https://spring.io/projects/spring-ai) application, designed to run large language models
locally via Ollama. Supports **MCP Servers** integration.

## ⚙️ Setup

1. Install [Ollama](https://ollama.com/download) on your system.
2. Run a model that supports tools, for example:
```bash
  ollama run qwen
```
3. Start the Spring Boot application:
```bash
  gradle bootRun
```

## 🦙 Ollama

When selecting a model, make sure it fits your use case:

- For general purpose choose [**any**](https://ollama.com/search)
- For **Tool Calling** or **MCP Servers** integrations choose model which support [**Tools**](https://ollama.com/search?c=tools).
- For **Image** or **Video** input choose the one which supports [**Vision**](https://ollama.com/search?c=vision)
