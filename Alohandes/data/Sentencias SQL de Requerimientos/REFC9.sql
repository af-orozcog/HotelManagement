SELECT o.id
FROM oferta o
WHERE o.id NOT IN
(
	SELECT re1.oferta
	FROM reserva re1
	WHERE (
			SELECT re2.id
			FROM reserva re2
			WHERE to_number(to_char(re2.inicio,'DDD')) > to_number(to_char(re1.fin,'DDD'))
			AND to_number(to_char(re1.fin,'DDD')) + 30 >  to_number(to_char(re2.inicio,'DDD')) 
			AND to_number(to_char(re1.fin,'YYYY')) = to_number(to_char(re2.inicio,'YYYY'))
		) IS NOT NULL
		OR (
			SELECT re2.id
			FROM reserva re2
			WHERE to_number(to_char(re2.inicio,'DDD')) < to_number(to_char(re1.fin,'DDD'))
			AND to_number(to_char(re1.fin,'DDD')) + 30 - 365 >  to_number(to_char(re2.inicio,'DDD'))
			AND to_number(to_char(re1.fin,'YYYY'))+1 = to_number(to_char(re2.inicio,'YYYY'))
		) IS NOT NULL

	GROUP BY re1.oferta
);