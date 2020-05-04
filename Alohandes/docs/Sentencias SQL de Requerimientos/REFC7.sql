SELECT SUM(cantidad) as monto FROM 
( GANANCIAS g INNER JOIN VIVIENDA vi ON g.operador = vi.operador )
WHERE vi.tipo = ?  AND to_number(to_char(g.fecha, 'iw')) = ? 

SELECT SUM(g.cantidad) as monto FROM 
( GANANCIAS g INNER JOIN  vi ON g.operador = vi.operador )  
WHERE vi.tipo = ?  AND to_number(to_char(g.fecha, 'MM')) = ? 

SELECT COUNT(re.id) as monto FROM 
((RESERVA re INNER JOIN OFERTA o ON re.oferta = o.id) INNER JOIN VIVIENDA vi ON vi.id = o.vivienda)
WHERE (vi.tipo = ?  AND TO_NUMBER(to_char(re.inicio, 'iw')) <= ? AND ? <= TO_NUMBER(to_char(re.fin, 'iw')))

SELECT COUNT(re.id) as monto FROM
((RESERVA re INNER JOIN OFERTA o ON re.oferta = o.id) INNER JOIN VIVIENDA vi ON vi.id = o.vivienda)
WHERE vi.tipo = ?  AND (to_number(to_char(re.inicio, 'MM')) <= ?) AND (? <= to_number(to_char(re.fin, 'MM')))

SELECT COUNT(re.id) as monto FROM 
((RESERVA re INNER JOIN OFERTA o ON re.oferta = o.id) INNER JOIN VIVIENDA vi ON vi.id = o.vivienda)
WHERE (vi.tipo = ?  AND TO_NUMBER(to_char(re.inicio, 'iw')) <= ? AND ? <= TO_NUMBER(to_char(re.fin, 'iw')))

SELECT COUNT(re.id) as monto FROM 
((RESERVA re INNER JOIN OFERTA o ON re.oferta = o.id) INNER JOIN VIVIENDA vi ON vi.id = o.vivienda)
WHERE vi.tipo = ?  AND (to_number(to_char(re.inicio, 'MM')) <= ?) AND (? <= to_number(to_char(re.fin, 'MM')))