# Travel SX

## 项目简介
这是一个基于 **Spring Boot 3** (后端) 和 **Vue 3** (前端) 的前后端分离项目，支持 Docker 容器化部署。

## 技术栈

### 后端 (Backend)
采用 Maven 多模块架构：
*   **API Module (`api`)**: Web 接口层，包含启动类。
*   **Service Module (`service`)**: 业务逻辑层。
*   **Infrastructure Module (`infrastructure`)**: 基础设施层，集成 MyBatis-Plus 和 MySQL。
*   **Common Module (`common`)**: 通用工具与常量。

**主要技术**:
*   Java 17
*   Spring Boot 3.2.0
*   MyBatis-Plus 3.5.9
*   MySQL Driver 8.0.33
*   Lombok

### 前端 (Frontend)
*   Vue 3
*   Vite
*   JavaScript

### 部署与运维
*   Docker & Docker Compose
*   Nginx (前端反向代理)

## 目录结构

```
travel-sx/
├── backend/                # 后端工程根目录
│   ├── api/                # 接口层
│   ├── service/            # 业务层
│   ├── infrastructure/     # 基础设施/DAO层
│   ├── common/             # 通用层
│   └── Dockerfile          # 后端构建镜像
├── frontend/               # 前端工程根目录
│   └── Dockerfile          # 前端构建镜像
├── docker-compose.yml      # 容器编排文件
└── README.md               # 项目说明文档
```

## 快速开始

### 使用 Docker 启动 (推荐)

项目根目录下执行：

```bash
docker-compose up --build
```

*   **前端访问**: http://localhost:5173
*   **后端接口**: http://localhost:8080

### 本地开发

#### 后端
1.  进入 `backend` 目录。
2.  确保配置好本地 MySQL 数据库（需修改 `infrastructure` 模块中的配置文件，目前尚未生成 `application.yml`）。
3.  运行 `mvn clean install`。
4.  启动 `api` 模块下的 `BackendApplication`。

#### 前端
1.  进入 `frontend` 目录。
2.  安装依赖: `npm install`。
3.  启动开发服务器: `npm run dev`。

## 推荐开发工具 (MCP Servers)

本项目推荐配置以下 MCP Server，以增强 AI 在开发过程中的能力。

### 1. 仓库管理 (GitHub)
*   **包名**: `@modelcontextprotocol/server-github`
*   **用途**: 允许 AI 直接访问 GitHub API，执行搜索代码、读取文件、管理 Issue/PR、创建分支等操作。
*   **必要性**: 使得代码审查和版本管理更自动化。

### 2. 网络搜索 (Brave Search)
*   **包名**: `@modelcontextprotocol/server-brave-search`
*   **用途**: 允许 AI 进行实时网络搜索，获取最新的技术文档、库版本信息和解决方案。
*   **必要性**: 解决 AI 训练数据截止导致的知识过时问题。

### 3. 网页抓取 (Fetch)
*   **包名**: `@modelcontextprotocol/server-fetch`
*   **用途**: 将指定的 URL 网页内容抓取并转换为 Markdown 格式供 AI 阅读。
*   **必要性**: 方便 AI 快速阅读和分析第三方文档或教程。

### 4. 数据库操作 (PostgreSQL)
*   **包名**: `@modelcontextprotocol/server-postgres`
*   **用途**: 允许 AI 连接数据库，查看表结构 (Schema)、执行 SQL 查询、进行数据分析。
*   **必要性**: 在后端开发中快速调试数据库和验证数据。

### 5. 记忆增强 (Memory)
*   **包名**: `@modelcontextprotocol/server-memory`
*   **用途**: 基于知识图谱的持久化记忆，让 AI 记住你的编码偏好、项目背景和特定指令。
*   **必要性**: 提高长期协作的连贯性。

### 参考配置 (cursor_mcp_config.json)

以下是用于 Cursor 设置的 JSON 配置参考（JSON 格式不支持注释，使用时请移除 `//` 注释）：

```jsonc
{
  // 1. GitHub: 用于管理远程仓库
  "github": {
    "command": "npx",
    "args": ["-y", "@modelcontextprotocol/server-github"],
    "env": {
      "GITHUB_PERSONAL_ACCESS_TOKEN": "<YOUR_TOKEN>" // 必填: 你的 GitHub Token
    }
  },
  // 2. Brave Search: 用于联网搜索最新技术
  "brave-search": {
    "command": "npx",
    "args": ["-y", "@modelcontextprotocol/server-brave-search"],
    "env": {
      "BRAVE_API_KEY": "<YOUR_API_KEY>" // 必填: 你的 Brave Search API Key
    }
  },
  // 3. Fetch: 用于抓取网页文档
  "fetch": {
    "command": "npx",
    "args": ["-y", "@modelcontextprotocol/server-fetch"]
  },
  // 4. PostgreSQL: 用于数据库调试
  "postgres": {
    "command": "npx",
    "args": [
      "-y", 
      "@modelcontextprotocol/server-postgres", 
      "postgres://username:password@localhost:5432/dbname" // 必填: 替换为实际数据库连接串
    ]
  },
  // 5. Memory: 用于记住项目上下文
  "memory": {
    "command": "npx",
    "args": ["-y", "@modelcontextprotocol/server-memory"]
  }
}
```

## 更新日志
- **2026-01-27**: 完善文档。
    - 详细说明 MCP Server 的用途及配置示例。
- **2026-01-27**: 初始化项目。
    -后端采用 Spring Boot 多模块架构。
    - 前端初始化 Vue 3 + Vite。
    - 配置 Dockerfile 及 Docker Compose。
