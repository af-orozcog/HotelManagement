--Selecciona el cliente a eliminar la reserva--
SELECT * FROM CLIENTE WHERE nombre = ?;

--Selecciona todas las reservas de ese cliente--
SELECT * FROM RESERVA WHERE cliente = ?;

--Se listan las reservas y se selecciona una--
DELETE FROM RESERVA WHERE id = ?;
