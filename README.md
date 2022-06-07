# Prometeo 🥇🥈🥉

Prometeo es una App que facilita la gestion de unos mini juegos Olimpicos

## Comenzando 🏁

_Estas instrucciones te permitirán obtener una copia del proyecto en funcionamiento en tu máquina local para propósitos de desarrollo y pruebas._

Mira **Deployment** para conocer como desplegar el proyecto.

### Pre-requisitos 📋

- openjdk version "1.8"
- Spring Framework 2.6.3
- Manejador de Base de Datos relacional MySQL 8.0
- Servidor Apache Tomcat 4.0.0
- IDE preferencia VS code

### Deployment 🚀

Lo primero que debe hacer es clonar el repositorio:

```
$ git clone https://github.com/Javatar-Hub/proyecto1.git
```

Crear una nueva base de datos en MySQL

```
create database db_proyecto_olimpiadas
use db_proyecto_olimpiadas
```
Definir el nombre de usuario y password para ingresar a MySQL en el archivo `application.properties`

```
spring.datasource.username=root
spring.datasource.password=password
```

Ejecute este comando para iniciar el servidor Backend 

```
mvn spring-boot:run 
```

Y navegue a http://127.0.0.1:8080

## Construido con 🛠️

- [Spring](https://spring.io/) - Spring hace que programar Java sea más rápido, fácil y seguro para todos. El enfoque de Spring en la velocidad, la simplicidad y la productividad lo ha convertido en el marco Java más popular del mundo.
- [Java](https://www.python.org/) - Java es un lenguaje de programación orientado a objetos que es muy utilizado para el desarrollo de aplicaciones.

## Autores ✒️
 
- **Victor Hugo Gallegos Mota** - _Plantilla inicial, CRUD Jueces y Login_ - [VictorDeGallegos](https://github.com/VictorDeGallegos)
- **José Demian Jiménez Salgado** - _CRUD Disciplinas y consulta de competidores_ - [demian35](https://github.com/demian35)
- **Carlos Cruz Rangel** - _CRUD Competidores, Registro entrenadores_ - [CarlosCruzRangel](https://github.com/CarlosCruzRangel)
- **Juan Manuel Díaz Quiñonez** - _Agregar comentarios consultar competidores_ - [JuanDiazDev](https://github.com/JuanDiazDev)
- **Toprak Memik Hernandez** - _Consultar calificaciones, consultar tabla de posiciones_ - [ToprakShakur](https://github.com/ToprakShakur)

## TO-DO LIST ✅

- [x] CRUD Jueces
- [x] Login
- [x] CRUD Disciplinas
- [x] Consultar Competidores
- [x] CRUD Competidores
- [x] Registro entrenadores
- [ ] Agregar comentarios
- [ ] Consultar competidores
- [ ] Consultar calificaciones
- [ ] Consultar tabla de posiciones


## Expresiones de Gratitud 🎁

- Comenta a otros sobre este proyecto 📢
- Invita una cerveza 🍺 o un café ☕ a alguien del equipo.
- 10/10 al proyecto 💯

---
⌨️ con ❤️ por [JAVATAR](https://github.com/Javatar-Hub) 😊
