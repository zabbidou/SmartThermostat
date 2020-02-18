.PHONY: build run

build: 
	javac -sourcepath src -d build src/tema2/*.java

run: build
	java -cp .:build:tema2/*.class tema2.Main

clean:
	rm ./build/tema2/*.class
