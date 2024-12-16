pour voir toutes les images 
docker images
pour voir les images en cours
docker ps
pour demarrer le container openjdk
docker run -it openjdk:22-jdk /bin/sh
pour voir les dossiers du container (le nom du container est ici crazy_babbage)
docker exec crazy_babbage ls -a
pour copier le jar dans tmp
docker cp .\target\rest-demo.jar crazy_babbage:/tmp
pour creer une image aprés les modifications apporté 
docker commit crazy_babbage telustko/rest-demo:v1
pour creer l'image et que quand on roule container le jar se lance
docker commit --change='CMD ["java","-jar","/tmp/rest-demo.jar"]' crazy_babbage telustko/rest-demo:v2
la cmde qui a marché pour moi
docker container commit --change='CMD java -jar /tmp/rest-demo.jar' crazy_babbage telustko/rest-demo:v2
pour rouler le container
docker run telustko/rest-demo:v2
pour le lancer sur le port 8081 de la machine physique
docker run -p 8081:8080  telustko/rest-demo:v2

Pour creer l'image avec un docker file, le creer d'abord puis faire
docker build -t telusko/rest-demo:v3 .
et c fini