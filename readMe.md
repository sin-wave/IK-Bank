# India Ka Bank - Microservices
### Spring Cloud Netflix's Eureka
- service which act as a service discovery
  -   it is mostly used because service registration and discovery are crucial components that enable seamless communication between different services. Netflix's Eureka Server, integrated with Spring Boot, provides an elegant solution for managing these aspects.
- Client Registration: Instances of microservices automatically register themselves with Eureka Server.
- Service Discovery: Eureka Server maintains a registry of all client applications running on different ports and IP addresses.
- there are other services registries such as etcd, Consul, and Apache Zookeeper
### Spring Cloud Load Balancer
- library for client side load balancing
### Netflix feign client
  - to look for a service b/w microservices
## GATEWAY SERVER
### Internal Architecture of GateWay
- first we get request from client(request in the form of mobile application/web application) which is working as a edge server inside our microservice. It will take help from Gateway Handler mapping using Routing configs(it is used to identify the path that has been invoked by my client application and to which microservice it has to be redirect the request).
- **My gateway handler** mapping it can't take a decision to which microservice it has to be forwarded the request.if someone send a request to a particular path, please redirect that request to a particular microservice.
- After my spring cloud gateway identifies to which microservice the request has to be forwarded first it will check if there are any predicates or pre-filter configured by the developer
- A **Predicates** is a logic which will return a boolean value, you can define some conditions to your spring cloud gateway, saying that if a particular condition is met then only forward the request to the microservice otherwise simply reject it with whatever reason.
- All such non-functional requirements are cross-cutting requirements, we can implements using **Prefilters**  once this **Prefilter** are executed my request will be forwared to the actual request. **Prefilters** will act on the request
- **PostFilters** they will acts on the response. After getting response from microservice we do some validation based on the business logic adn all these defines inside postfilters.
- After processing all the post filters we sent the response to gateway handler mapping.It will send to the actual client application 
---

### Header 3

- Bullet point
- Another point

**Bold text** and *italic text*.

[Link to IntelliJ IDEA](https://www.jetbrains.com/idea/)

```code block example```
