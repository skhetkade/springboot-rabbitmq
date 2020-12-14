# Springboot- Rabbitmq

RabbitMQ Docker image [link](https://hub.docker.com/_/rabbitmq)
```
# run a standalone instance using management
docker network create rabbits
docker run -d --rm --net rabbits --hostname my-rabbit -p 15672:15672 -p 5672:5672 --name rabbit-1 rabbitmq:3-management

# clean up
docker rm -f rabbit-1

# docker logs
docker logs rabbit-1
```

# SpringBoot Publisher/Consumer
```
mvn clean install

docker build . -t rabbitmq-pub-sub:latest

docker run -it --rm --net rabbits -p 8081:8081 rabbitmq-pub-sub:latest
```

# Message publishing endpoints
1. Ping [URL](localhost:8081/rabbitDemo/)
2. Order [URL](localhost:8081/rabbitDemo/order/unknown)
  ```
  Input JSON
  {
    "orderEntity":"laptops",
    "orderQty":"200",
    "orderETA":"30days"
  }
  ```
3. Ticket [URL](9localhost:8081/rabbitDemo/ticket)
  ```
  Input JSON
  {
    "ticketQty":"100"
  }
  ```
  
