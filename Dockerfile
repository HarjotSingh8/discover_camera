FROM openjdk:11

# RUN apt update && apt-get install net-tools -y
COPY . /usr/src/myapp
WORKDIR /usr/src/myapp
RUN jar xf /usr/src/myapp/lib/simple-xml-2.6.7.jar && \
    jar xf /usr/src/myapp/lib/evercam-java-1.0-jar-with-dependencies.jar && \
    jar xf /usr/src/myapp/lib/upnplib-mobile.jar
# RUN javac Main.java --module-path=/usr/src/myapp/lib/evercam-java-1.0-jar-with-dependencies.jar
RUN javac Main.java