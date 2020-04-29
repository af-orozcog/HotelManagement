SELECT *
FROM OFERTA
WHERE id in (SELECT oferta
                FROM  ( SELECT oferta, COUNT(id) as wut
                        FROM RESERVA
                        GROUP BY oferta
                        ORDER BY wut DESC)
                WHERE ROWNUM < 21);