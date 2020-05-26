SELECT DISTINCT c.id, c.nombre, c.email, c.numero, c.documento, c.tipo_cliente, v.tipo
FROM CLIENTE c, RESERVA r, OFERTA o, VIVIENDA v
WHERE c.id = r.cliente AND o.id = r.oferta AND o.vivienda = v.id AND o.id <> 117 AND
r.inicio BETWEEN TO_DATE('1/5/2020','DD/MM/YYYY') AND TO_DATE('30/8/2020','DD/MM/YYYY') AND
r.fin BETWEEN TO_DATE('1/5/2020','DD/MM/YYYY') AND TO_DATE('30/8/2020','DD/MM/YYYY')
ORDER BY v.tipo, c.tipo_cliente, c.nombre ASC;
