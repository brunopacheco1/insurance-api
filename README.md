# insurance-api

this is a Case Study for Element.

## Development process

I started the development thinking about the data structure and possible endpoint contracts.
After having an idea, I've downloaded an empty project from https://start.spring.io/.
Initially, I thought to implement also spring security, but after reading some articles online how to use Auth0 with SpringBoot I just thought it would take more time than necessary, so I'm assuming that I would have it configured on a real product.
I developed CRUD endpoints for Modules and Users and the endpoints to calculate prices and return historical prices by User. To check and try all endpoints, please refer to the link http://localhost:4000/swagger-ui.html.
By the way, I using Swagger to expose the API documentation. Again to save time, I didn't improve the API documentation, but it's possible adding especial annotations in the code.
I added javax.validation in the entities to help me on the request body validation. I could improve messages and responses, but again it wouldn't add much value to this task, but I would be worth on a real product.
I am testing only to the business logic to calculate prices and return price history by the user id.
I added Cacheable feature to the app just to show that I am concerned about possible performance/scalability problems and it's quite simple to make it working on Spring. It could be handled by the Cloud provider also, but it's out of scope for this task.
After finish the base app functionality, I added the Dockerfile and samples of TravisCI and Sonar configuration, just to exemplify how I would configure the CI/CD.
After having the docker image, it's possible to deploy the app anywhere, manually or triggering a deploy action in building step or having a hook on docker image repository. Everything depends on how the CI/CD process is configured.

## Building and testing the backend app
mvn clean package

## Building docker image and running locally
sudo docker build  -t "brunopacheco1/insurance-api" .
sudo docker run -p 4000:4000 --rm --name insurance-api-test -d brunopacheco1/insurance-api
