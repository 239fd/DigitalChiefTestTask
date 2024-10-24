# Инструкция по запуску

## Технологии

- Java 17
- Gradle 8.5
- Spring Boot 3.3.4
- PostgreSQL
- Docker, Docker compose
- ElasticSearch


## 1.Развертывание проекта

Используйте `git clone https://github.com/239fd/DigitalChiefTestTask.git` для клонирования репозитория в рабочую директорию.

Далее  используйте `cd DigitalChiefTestTask`.

После этого используйте `docker compose up -d` для развертывания PostgreSQL и ElasticSearch.

Далее `./gradlew bootRun`

## 2. Документация по API

- `GET http://localhost:8080/api/load/active`. Осуществляет загрузку файлов в индекс. Ответ в случае успешного выполнения: `Active products have been uploaded`
- `GET http://localhost:8080/api/load/afterStartDate?startDate=2024-12-12`. Осуществляет загрузку файлов в индекс после указанной даты. Ответ в случае успешного выполнения: `Products after 2024-12-12 have been loaded`
- `GET http://localhost:8080/api/products?query=16GB`. Осуществляет поиск файлов в индексе по полям `name` и `description`. Ответ:
```json
[
    {
        "id": 2,
        "name": "Laptop",
        "description": "Gaming laptop with 16GB RAM",
        "price": 1200.0,
        "active": true,
        "startDate": "2024-01-01T21:00:00.000+00:00",
        "skuList": [
            {
                "id": 5,
                "skuCode": "SKU005",
                "quantity": 60,
                "color": "White",
                "available": true,
                "weight": 1.2
            }
        ]
    }
]
```
- `POST http://localhost:8080/api/product`. Добавляет товар в базу данных (не в индекс). Тело запроса:
```json
{
  "name": "Monitor",
  "description": "HD monitor 1920x1080",
  "price": 249.99,
  "active": true,
  "startDate": "2024-01-01"
}

```
Ответ в данном случае: `Product created successfully`

- `DELETE http://localhost:8080/api/product/23`. Удаляет товар из базу данных. Ответ в данном случае: `Product deleted successfully`
- `GET http://localhost:8080/api/product`. Данный запрос получает все товары из базы данных.
- `POST http://localhost:8080/api/product/22/skus`. Данный запрос позволяет добавить ску к продукту. Тело запроса:
```json
{
  "skuCode": "SKU12345",
  "quantity": 50,
  "color": "Black",
  "available": true,
  "weight": 1.5
}
```
Пример ответа: `SKU added successfully`.

##p.s.
__________________________________
Для осуществления поиска по фильтрам необходимо изменить `filter.active` на `true` и другие параметры в файле `application.properties`.
