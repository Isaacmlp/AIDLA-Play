#  Reproductor de Musica y video AIDLA Music hecho en Java

_Me di la tarea de crear un reproductor de música y video en java, capaz de ejecutar una gran cantidad de formatos de audio y video
entre ellos tenemos: MP3, FLAC, M4A y OGG. Por la parte de audio y por la parte de video tenemos: MP4, MKV, AVI y FLV_.

_Teniendo funcionalidades como: elegir el directorio del archivo a reproducir, pausar, adelantar 10 seg, atrasar 10 seg , que la musica o video se repita constantemente, slider de volumen y un slider que se va actualizando dependiendo de en que punto se encuentro su muúsica o video._

## Comenzando 🚀

_Estas instrucciones te permitirán obtener una copia del proyecto en funcionamiento en tu máquina local para propósitos de desarrollo y pruebas._



### Pre-requisitos 📋

_Que cosas necesitas para instalar el software y como instalarlas_

```
Si vas a programar en base a este proyecto necesitaras JDK 11 
en adelante, la versión utilizada en este proyecto es JKD 21.
```
```
Si solo vas a ejecutar la aplicación necesitas instalar JRE.
```

```
Necesitaras un IDE para que te proporcione más facilidad y 
simplicidad.
```
### Instalación 🔧

_Una serie de ejemplos paso a paso que te dice lo que debes ejecutar para tener un entorno de desarrollo ejecutandose_


```
Para hacerlo más comodo desde tu terminal en linux y si es windows con Git
previamente instalo, clona el repositorio de la siguiente manera:

git clone https://github.com/Isaacmlp/AIDLA-Play.git 

tambien puedes utilizar las opciones de version de control de tu IDE
y abrir un nuevo proyecto clonando dicho repositorio,
```

```
Asegurate de tener la version de JDK correcta si no el IDE te la proporcionara
```

```
Abre el pom.xml para gestionar las dependecias de VLCJ y recarga el archivo de
configuraciones para que descargue las dependencias
```
```
En las lineas 152 a 156 del Package View en la clase View Asegurate de actualizar
la ruta que reciben como parametro la funcion
Reescalarimagen("","Aca inserta la ruta directa hacia la imagen  ");
Ejemplo: "C:\\ruta\\ruta\\ruta\\ruta\\ruta\\ruta\\Img\\Play.png"
para que asi carguen perfectamente los iconos.
```
```
Nota: Sera corregido en proximas versiones.
```


## Construido con 🛠️

* [IntelliJ IDEA Community Edition](https://www.jetbrains.com/idea/download/?section=windows) - entorno de desarrollo integrado para el desarrollo de programas informáticos
* [Maven](https://maven.apache.org/) - Manejador de dependencias
* [VLCJ](https://www.capricasoftware.co.uk/projects/vlcj) - Framework de VLC Utilizada para la Multimedia
* [MVC](https://es.wikipedia.org/wiki/Modelo–vista–controlador) - Patron de diseño


## Versionado 📌

Utiize [GitHub](https://github.com) para el versionado. Para todas las versiones disponibles, [Click aqui](https://github.com/Isaacmlp/AIDLA-Play).

## Autores ✒️

* **Isaac León** - *Diseño, Codificacion y desarrollo completo* - [Isaac León](https://github.com/Isaacmlp/)
---
Hecho por [Isaac León](https://github.com/Isaacmlp/) 😊