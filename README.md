## USE k8s-config for other configuration before executing it
`https://github.com/prabeensoti/k8s-config`

execute command to deploy on minikube

`minikube kubectl -- apply ./k8s/deployment.yaml`
### OR
`kubectl apply ./k8s/deployment.yaml`

### USE BELOW EXPOSED URL TO ACCESS THE SERVICE
## SIGNUP
`microservice.test/user/signup`

## LOGIN
### POST METHOD: microservice.test/user/login
### REQUEST BODY:
### `{"email": "admin@admin.com","password": "admin@123"}`
### RESPONSE BODY:
### `{"token":"jwt_token"}`