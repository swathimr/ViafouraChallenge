#### Validate Anagram and generate Anagram of a String

Rest'ful end points to validate anagram and to retrieve anagrams of a string

##### To run the application
1. mvn clean install and right click and run Application.java directly

2. Running using Docker Compose

Run `mvn clean package` to create the jar
Docker file should be pointing to the created jar name

To build the image,

`docker build . -t {image_name}`

`docker images` should list the built image

We can run the docker imagea in one of either way,
1. docker run -p 8000:8080 {image_name} or
2. docker-compose file pointing to created image and docker-compose up

