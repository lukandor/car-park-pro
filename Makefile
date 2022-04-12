service-up-compose = docker-compose

.PHONY: all.up
all.up:
	$(service-up-compose) build
	$(service-up-compose) up --build -d

.PHONY: down
down:
	$(service-up-compose) down

.PHONY: up
up:
	$(service-up-compose) up -d

# building PNGs from PUMLs
png: plantuml.jar docs/uml/*.png move.png

generate-all:
	find ./docs -name "*.puml" | xargs java -jar plantuml.jar {}
