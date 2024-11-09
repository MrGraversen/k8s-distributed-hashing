# K8s Distributed Hashing

## About

This project was created as a learning exercise to explore and understand the basics of deploying applications on Kubernetes. It
demonstrates a simple Spring Boot application for distributed hashing, where the Hashing App communicates with a Redis backend. This
repository serves as a reference for setting up core Kubernetes components like pods, deployments, services, and scaling.

By following this setup, you’ll gain hands-on experience with core Kubernetes workflows, which can be adapted or expanded as a foundation
for more advanced Kubernetes projects.

## Concepts

* **Pod**: The smallest deployable unit in Kubernetes, which runs one or more containers. Each instance of the Hashing App and Redis runs in
  its own pod.
* **Deployment**: Defines the desired state for application pods (e.g., the Hashing App) and manages the lifecycle of these pods, ensuring
  the correct number of replicas are always running.
* **Service**: A way to expose applications within or outside the cluster. In this project:
    * _Redis Service_: Uses a `ClusterIP` service type to make Redis accessible within the Kubernetes cluster.
    * _Hashing App Service_: Uses a `NodePort` to expose the app to external access.

## Setup

1. **Apply the Configurations:** To deploy Redis and the Hashing App, apply the necessary YAML files. Assuming your YAML files are named as
   follows, use:

```shell
kubectl apply -f redis-deployment.yaml -f redis-service.yaml -f app-deployment.yaml -f app-service.yaml
```

2. **Verify Resources:**

* Check if pods are running (both the Hashing App and Redis): `kubectl get pods`
* Check if services are correctly set up: `kubectl get svc`

3. **Access the Hashing App:**

* If using `NodePort`, access the app at `http://localhost:30080`.
* You can test the `/hash` endpoint with a `POST` request using `curl`:
*

```shell
curl -X POST http://localhost:30080/hash -H "Content-Type: application/json" -d '{"text": "example", "type": "SHA256", "rounds": 10}'
```

## Scaling the Hashing App

To scale the Hashing App, update the number of replicas in the deployment. For example, to scale to 3 replicas:

```shell
kubectl scale deployment hashing-app --replicas=3
```

Kubernetes will automatically distribute traffic to all replicas when accessed via the service.

## Tearing Down the Setup

To stop all resources related to the Hashing App and Redis, delete each resource with the following command:

```shell
kubectl delete -f redis-deployment.yaml -f redis-service.yaml -f app-deployment.yaml -f app-service.yaml
```

Alternatively, if you want to delete all resources in the namespace, you can use:

```shell
kubectl delete all --all
```

## Additional Tips

* **Namespace:** If using a custom namespace, specify it with the `-n <namespace>` option in each command, e.g.,
  `kubectl apply -f <file> -n my-namespace`.
* **Logs:** To check application logs, use `kubectl logs deployment/hashing-app`, which is helpful for debugging and monitoring.
* **DNS:** In Kubernetes, services within the same namespace can communicate via their names (e.g., `redis`), allowing the Hashing App to
  connect to Redis without needing the full domain.

This README provides a high-level overview of deploying, scaling, and managing the Hashing App and Redis on Kubernetes. With this setup, you
can extend, scale, or customize configurations as needed for production or testing environments. Happy Kubernetes-ing!