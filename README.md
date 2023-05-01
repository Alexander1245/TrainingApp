# Тестовое задание FitnessKit

Нужно реализовать экран расписания занятий клуба. Занятия должны быть отсортированы
по дате. В Recyclerview должны отображаться даты (EEEE, dd MMMM), а также сами вью 
занятия.
Список занятий можно получить по данному эндпоинту: 
https://olimpia.fitnesskit-admin.ru/schedule/get_v3/?club_id=2

Стек разработки: 
Kotlin, Coroutines, Flows, MVVM, Clean Architecture, Hilt, Retrofit2, <a href="https://github.com/Alexander1245/BaseMVVM">своя MVVM либа</a>

На разработку ушло 4.5 - 5 часов.

Реализованные фичи:
- Загрузка и кеширование Расписания
- Обработка ошибок (в т.ч. ошибок связанных с сетью)
- Поддержка темной темы

Скриншоты:
<p float="left">
  <img src="https://user-images.githubusercontent.com/36124349/235466356-7c8410c4-8fb2-4523-af83-37b72d41521b.jpg" width="100" />
  <img src="https://user-images.githubusercontent.com/36124349/235466363-0729a89d-a76f-462b-b387-8996651c0f55.jpg" width="100" /> 
  <img src="https://user-images.githubusercontent.com/36124349/235466380-2b27dba8-3b6f-4551-a21f-6284219b1ffa.jpg" width="100" />
  <img src="https://user-images.githubusercontent.com/36124349/235466393-7b673433-57cb-4058-8247-5584945f3f6b.jpg" width="100" />
</p>
