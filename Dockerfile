FROM openjdk:11
COPY . /usr/src/myapp
WORKDIR /usr/src/myapp
# RUN javac Main.java --module-path=/usr/src/myapp/lib/evercam-java-1.0-jar-with-dependencies.jar
# RUN javac Main.java