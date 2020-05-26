SELECT DISTINCT c.id, c.nombre, c.email, c.numero, c.documento, c.tipo_cliente
FROM CLIENTE c, RESERVA r, OFERTA o
WHERE c.id = r.cliente AND o.id = r.oferta AND o.id = ? AND
r.inicio BETWEEN ? AND ? AND
r.fin BETWEEN ? AND ?
ORDER BY v.tipo, c.tipo_cliente, c.nombre ASC;
