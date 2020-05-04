--Seleccionar operador a crearle oferta--
SELECT * FROM OPERADOR WHERE nombre = ? AND tipo_operador = ?;

--Si la vivienda de la oferta a registrar aun no ha sido creada --
INSERT INTO VIVIENDA (id, direccion, cupos, operador) values (?, ? ,? ,?);

--Caso 1 el operador es tipo HOTELERIA--
INSERT INTO HABITACION (id, tipo_habitacion, categoria, numero) values (? ,? ,? ,?);

--Caso 2 el operador es tipo PERSONA_NATURAL--

--Si es apto normal con arrendamiento mensual o semestral--
INSERT INTO APARTAMENTO (id, area, amoblado, numero_habitaciones) values (? ,? ,? ,?);
--Si es apto esporadico de solo unos dias--
INSERT INTO ESPORADICO (id, area, numero_habitaciones, noches_año, seguro, amoblado) values (?, ?, ?, ?, ?,?);

--Caso 3 el operador es tipo VIVIENDA_UNIVERSITARIA--
INSERT INTO CUARTO (id, baño_privado, cuarto_privado, esquema, menaje) values (? ,? ,? ,? ,?);

--Crea la oferta--
INSERT INTO OFERTA (id, precio, periodo, vivienda, fechainicio, fechafin) values (? ,? ,? ,? ,? ,?);

--Itera la siguiente secuencia si quiere añadir servicios a la oferta--
--Si no existe --
INSERT INTO SERVICIO (id, nombre, costo) values (? ,? ,?);

INSERT INTO INCLUYE (servicio, oferta, incluido) values (?, ?, ?);
	