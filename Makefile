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
