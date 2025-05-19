# Smart Agent

Smart Agent is a [**Spring AI**](https://spring.io/projects/spring-ai) application, designed to run large language models locally via Ollama.

---

## ✨ Features

- [Conversation Memory](https://docs.spring.io/spring-ai/reference/api/chat-memory.html#_message_window_chat_memory) (last 20 messages)
- [MCP Servers integration](https://docs.spring.io/spring-ai/reference/api/mcp/mcp-client-boot-starter-docs.html)
  - [Sequential Thinking](https://github.com/modelcontextprotocol/servers/tree/main/src/sequentialthinking) - dynamic and reflective problem-solving through a structured thinking process.
  - [Web Content Fetching](https://github.com/modelcontextprotocol/servers/tree/main/src/fetch) - retrieve and process content from web pages.
  - [Time with Timezone](https://github.com/modelcontextprotocol/servers/tree/main/src/time) - provides time and timezone conversion capabilities.
  - [Context7](https://github.com/upstash/context7) - version-specific documentation and code examples straight from the source.

---

## ⚙️ Setup

1. Install [Ollama](https://ollama.com/download).
2. Run a model that supports tools: ```ollama run qwen```.
3. Start the Spring Boot application: ```gradle bootRun```.

---

## 🦙 Ollama

When selecting a model, make sure it fits your use case:

- For general purpose choose [**any**](https://ollama.com/search)
- For **Tool Calling** or **MCP Servers** integration choose model which support [**Tools**](https://ollama.com/search?c=tools).
- For **Image** or **Video** input choose the one which supports [**Vision**](https://ollama.com/search?c=vision)
