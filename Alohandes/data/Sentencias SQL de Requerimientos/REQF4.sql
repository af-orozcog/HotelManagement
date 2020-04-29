--Selecciona el cliente a hacer la reserva--
SELECT * FROM CLIENTE WHERE nombre = ?;

--Se saca la lista de todas las ofertas--
SELECT * FROM OFERTA;

--Si el cliente no tiene decidido la oferta se le itera preguntando que servicios desea--

--Lista de ofertas que no cumplen con el servicio deseado--
SELECT DISTINCT * FROM OFERTA o, INCLUYE i, SERVICIO s
WHERE o.id = i.oferta AND i.servicio = s.id AND s.nombre <> ?;

--De toda la lista de ofertas se eliminan todas las ofertas que pertenezcan a las lista de ofertas que no cumplen--


--Cuando el cliente ya tenga el id de la oferta que desea luego se crea la reserva--
SELECT * FROM OFERTA WHERE id = ?;
INSERT INTO RESERVA (id, inicio, fin, periodo_arrendamiento, cliente, oferta) values (? ,? ,? ,? ,? ,?);