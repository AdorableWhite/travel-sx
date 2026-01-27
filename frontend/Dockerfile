FROM node:20-alpine AS build
WORKDIR /app
# Configure npm mirror for faster downloads in China
RUN npm config set registry https://registry.npmmirror.com

COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build

FROM nginx:alpine
COPY --from=build /app/dist /usr/share/nginx/html
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
