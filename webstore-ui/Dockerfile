FROM node:17
RUN mkdir -p /home/node/app/node_modules && chown -R node:node /home/node/app
WORKDIR /home/node/app
COPY --chown=node:node package*.json ./
COPY --chown=node:node bower.json ./
COPY --chown=node:node server.js ./
USER node
RUN npm install
COPY --chown=node:node . .
EXPOSE 8080
CMD [ "npm", "start" ]