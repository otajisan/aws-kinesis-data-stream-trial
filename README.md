# aws-kinesis-data-stream-trial
Trial for AWS Kinesis Data Stream

## Send message

```bash
# Simple
curl -XPOST localhost:18080/kinesis/produce/test

# Reactive
curl -XPOST localhost:18080/kinesis/produceReactive/hello
```

```
Received message: Hello World
Received message: test
Received message: Hello World
Received message: Hello World
Received message: Hello World
Received message: Hello World
Received message: Hello World
Received message: Hello World
Received message: Hello World
Received message: GreetingMessage(message=hello)
```
