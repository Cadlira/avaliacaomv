FROM nginx:1.13.3-alpine

COPY nginx/default.conf /etc/nginx/conf.d/

RUN rm -rf /usr/share/nginx/html/*

COPY dist /usr/share/nginx/html

RUN echo "{\"url\":\"${API_URL:-http://localhost:3001/}\"}" > /usr/share/nginx/html/assets/apiUrl.json

CMD ["nginx", "-g", "daemon off;"]