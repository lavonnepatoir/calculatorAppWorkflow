# Use a base image
FROM node:16

# Set the working directory
WORKDIR /app

# Copy package files and install dependencies
COPY package.json package-lock.json ./

RUN npm install

# Copy the rest of the application
COPY . .

# Expose port (if applicable)
EXPOSE 3000

# Start the application
CMD ["npm", "start"]
