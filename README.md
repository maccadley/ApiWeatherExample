# ApiWeatherExample
ApiWeatherExample



localhost:8080/api/getAll - Получить все данные с ApiWeatherExample, сохранить отфильтрованные данные в БД 
localhost:8080/api/addblock?block=  - добавить слово в черный список. Новости, содержащие слово из черного списка в summary не будет сохранен в БД
localhost:8080/api/removeblock?unblock= - удалить слово из черного списка (Если существует)
localhost:8080/api/id?id= - поиск новости по ID
localhost:8080/api/newssite?newssite= - поиск списка новостей по определенному новостному сайту сайту
