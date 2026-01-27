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

本项目推荐使用以下 MCP Server 以增强 AI 辅助开发能力：

*   **GitHub**: 仓库管理 (`@modelcontextprotocol/server-github`)
*   **Brave Search**: 网络搜索 (`@modelcontextprotocol/server-brave-search`)
*   **Fetch**: 网页内容抓取 (`@modelcontextprotocol/server-fetch`)
*   **PostgreSQL**: 数据库操作 (`@modelcontextprotocol/server-postgres`)
*   **Memory**: 记忆增强 (`@modelcontextprotocol/server-memory`)

## 更新日志
- **2026-01-27**: 更新文档。
    - 添加推荐的 MCP Servers 列表。
- **2026-01-27**: 初始化项目。
    -后端采用 Spring Boot 多模块架构。
    - 前端初始化 Vue 3 + Vite。
    - 配置 Dockerfile 及 Docker Compose。
