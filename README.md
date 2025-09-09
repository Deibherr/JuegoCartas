# Juego de Cartas

Un juego de cartas desarrollado en Java con interfaz gráfica que permite a dos jugadores recibir cartas y formar grupos para obtener puntos.

## Descripción del Juego

Este proyecto implementa un sistema de juego de cartas donde cada jugador recibe 10 cartas de un mazo estándar de 52 cartas. El objetivo es formar grupos de cartas para reducir los puntos en la mano.

### Características Principales

- **Interfaz Gráfica**: Ventana con pestañas para dos jugadores
- **Sistema de Reparto**: Distribución aleatoria de 10 cartas por jugador
- **Detección de Grupos**: Identificación automática de pares, ternas, cuartetos y escaleras
- **Sistema de Puntuación**: Cálculo de puntos basado en cartas que no forman grupos

## Reglas del Juego

### Sistema de Puntuación
- **A, J, Q, K**: 10 puntos cada uno
- **Del 2 al 9**: Su valor numérico (2=2 puntos, 3=3 puntos, etc.)

### Tipos de Grupos

#### Grupos por Nombre (mismo valor)
- **Pares**: 2 cartas del mismo nombre
- **Ternas**: 3 cartas del mismo nombre  
- **Cuartetos**: 4 cartas del mismo nombre

#### Grupos por Secuencia (escaleras)
- **Escaleras**: 2 o más cartas consecutivas de la misma pinta

### Cálculo de Puntos
Solo las cartas que **NO** forman parte de ningún grupo (pares, ternas, cuartetos o escaleras) cuentan para la puntuación final.

## Estructura del Proyecto

```
src/
├── App.java              # Clase principal del juego
├── FrmJuego.java         # Interfaz gráfica principal
├── Jugador.java          # Lógica del jugador y detección de grupos
├── Carta.java            # Representación de una carta individual
├── NombreCarta.java      # Enum con los nombres de las cartas (AS, DOS, ..., KING)
├── Pinta.java            # Enum con las pintas (TREBOL, PICA, CORAZON, DIAMANTE)
├── Grupo.java            # Enum para tipos de grupos
└── deck/                 # Imágenes de las cartas (CARTA1.JPG - CARTA52.JPG)
```

## Cómo Jugar

1. **Ejecutar el juego**: Lanzar la aplicación Java
2. **Repartir cartas**: Hacer clic en "Repartir" para dar 10 cartas a cada jugador
3. **Verificar mano**: Seleccionar la pestaña del jugador y hacer clic en "Verificar"
4. **Ver resultados**: El sistema mostrará:
   - Grupos encontrados (pares, ternas, cuartetos, escaleras)
   - Cartas que sobran (no están en grupos)
   - Puntos totales

## Tecnologías Utilizadas

- **Java**: Lenguaje de programación principal
- **Swing**: Para la interfaz gráfica
- **AWT**: Para el manejo de eventos y componentes visuales

## Requisitos del Sistema

- Java 8 o superior
- Entorno de desarrollo Java (JDK)

## Compilación y Ejecución

```bash
# Compilar el proyecto
javac -d bin src/*.java

# Ejecutar el juego
java -cp bin App
```

## Características Técnicas

- **Prevención de cartas repetidas**: Uso de `Collections.shuffle()` para evitar duplicados
- **Detección inteligente de grupos**: Algoritmos para identificar pares, ternas, cuartetos y escaleras
- **Interfaz intuitiva**: Pestañas separadas para cada jugador con colores distintivos
- **Sistema de eventos**: Botones interactivos para repartir y verificar cartas

## Contribuciones

Este proyecto fue desarrollado como parte de un ejercicio de programación en Java, implementando conceptos de programación orientada a objetos e interfaces gráficas.
