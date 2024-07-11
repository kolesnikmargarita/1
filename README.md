# CLEVERTEC BACK TEST

##ИНСТРУКЦИЯ ПО ЗАПУСКУ

рабочий вариант - нажать зеленую стрелку в intellij idea

## Загрузить образ:

```shell
docker build -t clevertec_test_1 .
```

## Развернуть используя Docker Compose:
```shell
docker compose up backend -d
```

## Остановить работу проекта:

```shell
docker compose down
```