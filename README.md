# YandeRe-Core
Spring Framework and PaperMC in one core
## Russian
```
Эта версия ядра PaperMc имеет поддержку Spring Framework
и несколько полезных утилит для быстрого
развертывания web-страниц.
```
- plus.utl.Cfg.Config - полное управление конфигурацией
```
Есть поддержка автоматической перезагрузки, сохранения,
и записи если отсутствует необходиме значение.
```
- plus.YandeRe.CustomPages
```
Встроенный плагин для ускорения создания статичных и динамичных web страниц.
Используй CustomPages.add(implements plus.YandeRe.Page page) для того чтобы добавить
предоставить доступ к своей странице по ссылке похожей на

http://your_domain/?page=your_page:arguments
```
- plus.YandeRe.web.ReloadablePage - перезагружаемый макет страницы.
```
Шаблон страницы, который может быть перезагружен во время рантайма
```
- Создание плагинов
```
Процесс создания плагинов аналогичен созданию Java плагинов для Bukkit/Spigot
Пример плагина в Example.java
```
