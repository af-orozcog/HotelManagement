SELECT *
FROM CLIENTE
WHERE id in 
(
	SELECT cliente
	FROM 
	(
		SELECT re.cliente as cliente
		FROM (RESERVA re INNER JOIN OFERTA o ON o.id = re.oferta)
		WHERE o.vivienda = ?
		GROUP BY re.cliente
		HAVING COUNT(re.id) > 2
	)
	UNION
	(
		SELECT cliente as cliente
		FROM(
			SELECT re.cliente as cliente,TRUNC(re.fin) - TRUNC(re.inicio) as days
			FROM (RESERVA re INNER JOIN OFERTA o ON o.id = re.oferta)
			WHERE o.vivienda = ?
			GROUP BY re.cliente,re.inicio,re.fin
			)
		GROUP BY cliente
		HAVING SUM(days) > 14
	)
);
