--Selecciona el operador a eliminar la reserva--
SELECT * FROM OPERADOR WHERE nombre = ?;

--Selecciona todas las ofertas de ese operador--
SELECT * FROM OFERTA WHERE operador = ?;

--Se listan las ofertas y se selecciona una--
DELETE FROM OFERTA WHERE id = ?;
