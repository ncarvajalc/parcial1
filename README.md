
# Parcial práctico - Sección 1 - 202110

## Instrucciones

1. Haga un fork de este repositorio
2. Clone el repositorio bifurcado en su máquina virtual
3. Abra los proyectos en Netbeans
4. En Netbeans vaya a _Services > Databases > JavaDB_ y cree una base de datos que se llame _parcial_ (los demás campos déjelos en blanco)
5. Ejecute Clean & Build sobre el proyecto principal _s1_parcial_ y confirme que el resultado es exitoso.

Recuerde, este es un examen individual, ante cualquier sospecha de fraude su nota será cero.

## Contexto

Esta aplicación tiene como propósito gestionar la venta de artículos deportivos. Su objetivo en este examen es agregar la funcionalidad que permita **crear artículos deportivos asociados a un deporte**.

Para esto la aplicación ya cuenta con la entidad SportEntity y los métodos de persistencia y lógica necesarios para el examen. Favor revisar la carpeta de persistencia y lógica antes de empezar.

## Punto 1 (20%). Persistencia

(10%) Cree la entidad _ProductEntity_ en el paquete correspondiente. Un producto tiene un nombre, una descripción, un valor en pesos por unidad, una cantidad de unidades, si es un producto único o no, un deporte (La relación es uno a muchos, donde un deporte tiene muchos productos y un producto tiene un deporte) y un id de tipo _Long_ que representa su llave primaria.
 
(10%) Implemente la persistencia de la entidad (Solo el método de crear).

## Punto 2 (50%). Lógica

Usted debe crear la lógica de producto que cubra las siguientes reglas de negocio:

* El nombre no puede ser vacío o nulo.
* El valor del producto no puede ser cero si el producto tiene unidades.
* Ni el valor ni las unidades pueden ser negativas.
* No se permiten productos únicos para el deporte 'Futbol'.
* El deporte no puede ser nulo

(25%) Crear el método en la capa de lógica que valide las reglas de negocio y solicita persistir en caso de que todas pasen (sólo para el método crear).

(25%) Crear al menos tres pruebas unitarias: una que valida el escenario ideal en que todas las reglas de negocio se aprueban, y otras dos en las que las reglas de negocio fallan (las dos que usted decida). Si las reglas de negocio se cumplen, se debe llamar la persistencia para que el objeto sea persistido, de lo contrario debe lanzar una excepción _BusinessLogicException_ con un mensaje donde se especifique el problema.

## Punto 3 (30%). API

(5%) Modifique la clase _ProductDTO_ y agregue los atributos correspondientes, los getters, los setters y un constructor vacío. En nuestra decisión de diseño el DTO contendrá la relación y no necesitamos una clase Detail, por lo que este DTO debe estar relacionado con la clase SportDTO.
 
(5%) Cree el método toEntity que retorna un objeto _ProductEntity_ con los datos del objeto _ProductDTO_. Tenga presente la asociación.
 
(5%) Agregue el método constructor que recibe un _ProductEntity_ y haga el mapeo correspondiente entre ambas clases. Tenga presente la asociación.
 
(10%) Cree la clase _ProductResource_ con el método createProduct. Tenga presente que para crear un producto se requiere el deporte, por lo que la ruta es de la forma /sports/:id/products. En este método usted debe:
1. Validar que el deporte existe y sino devolver una excepción _WebApplicationException_.
2. Llamar al método de la lógica que crea la entidad, y retorne al usuario el nuevo objeto creado. 

(5%) Haga las pruebas de Postman para la creación de un nuevo recurso. En el proyecto cree una carpeta “images” y suba allí las imágenes de las pruebas. Deberá haber mínimo tres pruebas, una donde se cree correctamente el recurso y otras dos donde falle la creación por violación a las reglas de negocio.

Para esto, ejecute el archivo sql que se encuentra en s1_parcial-api > sql > s1_parcial1.sql. Para verlo debe ver el proyecto usando la pestaña Files.

### Prueba 1. Creación de un producto con un deporte no existente:

```
Method: POST
URL: http://localhost:8080/s4_parcial-api/api/sports/1/products
Body:
{
    "nombre": "Camiseta de Colombia",
    "descripción": "Camiseta original de la selección Colombia."
    "unidades": 100,
    "valor": 120000,
    "unico": false
}
Response: 404
```

### Prueba 2. Creación de un producto correctamente:

```
Method: POST
URL: http://localhost:8080/s4_parcial-api/api/sports/200/products
Body:
{
    "nombre": "Camiseta de Colombia",
    "descripción": "Camiseta original de la selección Colombia."
    "unidades": 100,
    "valor": 120000,
    "unico": false
}
Response: 200
```

### Prueba 3. Creación incorrecta por nombre no válido:

```
Method: POST
URL: http://localhost:8080/s4_parcial-api/api/sports/200/products
Body:
{
    "descripción": "Camiseta original de la selección Colombia."
    "unidades": 100,
    "valor": 120000,
    "unico": false
}
Response: 412
```

## Entrega

1. Agregue los pantallazos de las pruebas de Postman a la carpeta images de su repositorio

2. Haga commit y push a la rama master de su repo

3. Cree un release de su código con el nombre "Parcial1_S4_2603". 

4. Suba el archivo zip del release como respuesta a la evaluación en Brightspace
