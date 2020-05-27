
---Los buenos clientes son de tres tipos: aquellos que hacen reservas en AlohAndes al menos una vez al mes, CHECK
---aquellos que siempre reservan alojamientos costosos (Entiéndase costoso, por ejemplo, como mayor a USD
---150 por noche) y aquellos que siempre reservan suites. Esta consulta retorna toda la información de dichos
---clientes, incluyendo la que justifica su calificación como buenos clientes. Esta operación es realizada
---únicamente por el gerente general de AlohAndes

select c.id cliente, count(*) suites --Saco las reservas de suites del man
from reserva r, oferta o, cliente c, vivienda v, habitacion h
WHERE  r.oferta=o.id and o.vivienda=v.id and r.cliente=c.id and v.id=h.id and h.tipo_habitacion='SUITE'
group by c.id;
WITH T0 AS(
select cliente, count(distinct to_char(inicio,'mm/yy')) as reservas --Las reservas en distintos meses del mk ese.
 from reserva
 group by cliente
 order by reservas desc),

  T1 AS(
 select cliente, min(inicio) as primer_reserva-- Primer fecha en la que el mk ese reservó
 from reserva
 group by cliente
 ),
 
T2  as (  -- Cuenta cuántos meses han transcurrido desde su primer reserva
SELECT cliente, MONTHS_BETWEEN 
   (TO_DATE('05/2020','mm/yyyy'),
    to_date(to_char(primer_reserva, 'mm/yyyy'),'mm/yyyy'))
      meses_primer_reserva
    FROM T1 ),
T3 as(SELECT t1.CLIENTE , 1 reservas_xmes,meses_primer_reserva   --Resumen de las 3 primeras
FROM T1,T2,T0 
WHERE T1.CLIENTE=T2.CLIENTE AND T2.CLIENTE=T0.CLIENTE AND RESERVAS=MESES_PRIMER_RESERVA),
T4 AS(
select c.id cliente, count(*) reservascostosas --Saco las reservas costosas del man
from reserva r, oferta o, cliente c
WHERE  r.oferta=o.id and o.precio>20 and r.cliente=c.id 
group by c.id),
T5 AS(SELECT CLIENTE, COUNT(*) reservastotales FROM RESERVA GROUP BY CLIENTE   --Saco todas las reservas
),
T6 AS(
SELECT t4.cliente, 1 reservacostoso,reservascostosas FROM T4,T5 WHERE reservastotales=reservascostosas and t4.cliente=t5.cliente), --Resumen de las dos anteriores 
T7 AS(
select c.id cliente, count(*) suites --Saco las reservas de suites del man
from reserva r, oferta o, cliente c, vivienda v, habitacion h
WHERE  r.oferta=o.id and o.vivienda=v.id and r.cliente=c.id and v.id=h.id and h.tipo_habitacion='SUITE'
group by c.id),
T8 AS(
SELECT t5.cliente, 1 reservasuite, suites FROM T7,T5 WHERE reservastotales=suites and t7.cliente=t5.cliente)



SELECT cliente.id, cliente.nombre,cliente.tipo_cliente,t5.reservastotales, case when reservasuite=1 then 'hace reservas de suites' when reservacostoso=1 then 'gasta bastante dinero' when reservas_xmes=1 then 'hace muchas reservaciones' end motivo, reservastotales CANTIDAD_RESERVAS,nvl(SUITES,0) suites, nvl(reservascostosas,0) reservas_costosas,t2.meses_primer_reserva
FROM  cliente inner join t5 on cliente.id=t5.cliente inner join t2 on cliente.id=t2.cliente left outer join T6 on cliente.id=t6.cliente left outer join t3 on t3.cliente=cliente.id left outer join t8 on t8.cliente=cliente.id where reservacostoso is not null or reservas_xmes is not null or reservasuite is not null;