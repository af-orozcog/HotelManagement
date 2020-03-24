

--caso 1 persona natural --
INSERT INTO OPERADOR (id, nombre, email, numero, tipo_operador) values (? ,? ,? ,? ,'PERSONA_NATURAL');
INSERT INTO PERSONA_NATURAL (id, documento, tipo_persona) values (?,?,?);

--caso 2 hoteleria --
INSERT INTO OPERADOR (id, nombre, email, numero, tipo_operador) values (? ,? ,? ,? ,'HOTELERIA');
INSERT INTO HOTELERIA (id, tipo_hoteleria, hora_apertura, hora_cierre) values (? ,? ,? ,?);

--caso 3 vivienda universitaria --
INSERT INTO OPERADOR (id, nombre, email, numero, tipo_operador) values (? ,? ,? ,? ,'VIVIENDA_UNIVERSITARIA');

