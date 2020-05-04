--Selecciona las ofertas aptas
SELECT i.oferta, s.nombre 
 FROM INCLUYE i, SERVICIO s, OFERTA o, VIVIENDA v
 WHERE i.servicio = s.id AND i.oferta = o.id AND o.vivienda = v.id AND o.habilitada = 1
 AND v.tipo = ? AND o.periodo = ? AND fechaInicio <= ? AND fechaFin >= ? AND o.id NOT IN 
 (
 SELECT o.id FROM OFERTA o, RESERVA r
 WHERE o.id = r.oferta AND ((r.inicio >= ?  AND r.inicio <= ?) OR (r.inicio <= ? AND r.fin >= ?))
 )
 ORDER BY oferta
--Por java verifica que estas ofertas tengan los servicios especificados

--Crea reserva colectiva
INSERT INTO RESERVA COLECTIVA (id, fecha_realizacion, cantidad, cliente) values (? ,? ,? ,?)

--Crea todas las reservas
INSERT INTO RESERVA (id, inicio, fin, periodo_arrendamiento, cliente, oferta, colectiva) values (? ,? ,? ,? ,? ,?, ?)