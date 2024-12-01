Since you're on Windows, we’ll ensure everything is compatible with your system while working on the **DevPortfolio project**. Let’s adapt the steps for your setup.

---

## Step 1: **Prepare Your Environment**
1. **Install Docker Desktop**:
   - Download and install Docker Desktop for Windows from [here](https://www.docker.com/products/docker-desktop/).
   - Enable **WSL 2** if required (the Docker installer will guide you).

2. **Install Git**:
   - Download and install Git for Windows from [here](https://git-scm.com/downloads).

3. **Clone the Repository**:
   ```bash
   git clone https://github.com/ryanfitzgerald/devportfolio.git
   cd devportfolio
   ```

---

## Step 2: **Test Docker Locally**
Ensure Docker is running before proceeding.

### 1. **Write the `Dockerfile`**
Create a new file named `Dockerfile` in the root of the project folder:
```dockerfile
# Use the official Nginx image
FROM nginx:alpine

# Copy the website files to Nginx's default directory
COPY . /usr/share/nginx/html

# Expose port 80
EXPOSE 80

# Start Nginx
CMD ["nginx", "-g", "daemon off;"]
```

### 2. **Build and Run the Docker Container**
Run the following commands in your terminal:
```bash
docker build -t devportfolio .
docker run -d -p 8080:80 devportfolio
```

- Open your browser and go to `http://localhost:8080`.  
  You should see the portfolio website running.

---

## Step 3: **Set Up CI/CD Using GitHub Actions**

### 1. **Create a Workflow File**
In the project folder, create `.github/workflows/deploy.yml` (create folders if needed):
```yaml
name: Deploy Portfolio

on:
  push:
    branches:
      - main

jobs:
  build-and-deploy:
    runs-on: ubuntu-latest

    steps:
      # Checkout code
      - name: Checkout code
        uses: actions/checkout@v3

      # Build Docker image
      - name: Build Docker Image
        run: docker build -t devportfolio .

      # Push Docker image to Docker Hub
      - name: Push to Docker Hub
        env:
          DOCKER_USERNAME: ${{ secrets.DOCKER_USERNAME }}
          DOCKER_PASSWORD: ${{ secrets.DOCKER_PASSWORD }}
        run: |
          echo $DOCKER_PASSWORD | docker login -u $DOCKER_USERNAME --password-stdin
          docker tag devportfolio <your-dockerhub-username>/devportfolio:latest
          docker push <your-dockerhub-username>/devportfolio:latest
```

### 2. **Add Secrets to GitHub**
1. Go to your repository settings on GitHub.
2. Navigate to **Settings > Secrets and Variables > Actions**.
3. Add the following secrets:
   - `DOCKER_USERNAME`: Your Docker Hub username.
   - `DOCKER_PASSWORD`: Your Docker Hub password.

---

## Step 4: **Deploy the Docker Image**
You can deploy the image to:
1. **Railway**:
   - Push your project to GitHub.
   - Connect Railway to your GitHub repo.
   - Deploy directly using the Dockerfile.
2. **Docker Hub**:
   - The GitHub Actions workflow will push the Docker image to your Docker Hub account.
   - Use platforms like AWS, Azure, or GCP to deploy the image.

---

## Step 5: **Run and Verify**
1. Push changes to the `main` branch.
2. GitHub Actions will automatically build and push the Docker image.
3. Check the Docker Hub repository to confirm the image is uploaded.

---

Need help configuring Docker on Windows or deploying the project to a specific platform?