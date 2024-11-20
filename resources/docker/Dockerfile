# Base Image 
FROM nginx:alpine
# MAINTAINER of the Dockerfile
MAINTAINER HoaNguyen
#Copy the index.html file /usr/share/nginx/html/
COPY index.html /usr/share/nginx/html/
#Expose Nginx Port
EXPOSE 80
#Start NginxService 
CMD ["nginx", "-g", "daemon off;"]
